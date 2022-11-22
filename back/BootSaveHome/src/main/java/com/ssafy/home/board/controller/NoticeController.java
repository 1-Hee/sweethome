package com.ssafy.home.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.home.board.dto.FileInfoDto;
import com.ssafy.home.board.dto.Notice;
import com.ssafy.home.board.dto.Search;
import com.ssafy.home.board.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Map<String, String> map;
	private final NoticeService noticeService;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	public NoticeController(NoticeService service) {
		this.noticeService = service;
	}
	
	@GetMapping("/index/recommend") 
	public ResponseEntity<?> getTopFourLike() {
		try {
			List<Notice> list = noticeService.getTopFourLike();
			return new ResponseEntity<List<Notice>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("list")
	private ResponseEntity<?> list(Search search) throws Exception {
		PageHelper.startPage(search);
		List<Notice> list = noticeService.listArticle();
		return new ResponseEntity<PageInfo>(PageInfo.of(list),HttpStatus.OK);
	}

	@PostMapping(value = "write", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	private ResponseEntity<?> write(@RequestPart Notice notice, @RequestPart MultipartFile[] files) throws Exception {
			noticeService.writeArticle(notice);
			if(!files[0].isEmpty()) {
				String realPath = servletContext.getRealPath("/upload");
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
				String saveFolder = realPath + File.separator + today;
				File folder = new File(saveFolder);
				if(!folder.exists()) folder.mkdirs();
				for(MultipartFile mfile : files) {
					FileInfoDto fileInfoDto = new FileInfoDto();
					String originalFilename = mfile.getOriginalFilename();
					if(!originalFilename.isEmpty()) {
						String saveFileName = Long.toString(System.nanoTime())
								+ originalFilename.substring(originalFilename.lastIndexOf('.'));
						fileInfoDto.setArticleNo(notice.getArticleNo());
						fileInfoDto.setSaveFolder(today);
						fileInfoDto.setOriginalFile(originalFilename);
						fileInfoDto.setSaveFile(saveFileName);
						mfile.transferTo(new File(folder, saveFileName));
					}
					noticeService.addFileInfo(fileInfoDto);
				}
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("view/{no}")
	private ResponseEntity<?> view(@PathVariable("no") int no) throws Exception {
			Map<String, Object> resultMap = new HashMap<>();
			Notice notice = noticeService.getArticle(no);
			noticeService.updateHit(no);
			List<FileInfoDto> list = noticeService.getFiles(no);
			resultMap.put("notice", notice);
			resultMap.put("list", list);
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@PutMapping("modify/{no}")
	private ResponseEntity<?> mvModify(@PathVariable("no") int no, @RequestBody Notice notice) throws Exception {
		Notice notice2 = noticeService.getArticle(no);
		notice2.setTitle(notice.getTitle());
		notice2.setContent(notice.getContent());
		noticeService.modifyArticle(notice2);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("delete/{no}")
	private ResponseEntity<?> delete(@PathVariable("no") int no) throws Exception {
		noticeService.deleteArticle(no);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("download")
	private ResponseEntity<?> download(@RequestBody FileInfoDto file, HttpServletRequest request) throws Exception {
		String realPath = servletContext.getRealPath("/upload");
		File file2 = new File(realPath + File.separator + file.getSaveFolder(), file.getSaveFile());
		String UAheader = request.getHeader("User-Agent");
		boolean isIE = UAheader.indexOf("MSIE") > -1 || UAheader.indexOf("Trident") > -1;
        String fileName = null;
        if (isIE) {
        	fileName = URLEncoder.encode(file.getOriginalFile(), "UTF-8").replaceAll("\\+", "%20");
        } else {
            fileName = new String(file.getOriginalFile().getBytes("UTF-8"), "ISO-8859-1");
        }
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\";");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        header.add(HttpHeaders.TRANSFER_ENCODING, "binary");
        InputStreamResource resource3 = new InputStreamResource(new FileInputStream(file2));
        return ResponseEntity.ok()
        		.headers(header)
        		.contentLength(file2.length())
        		.contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource3);
	}
}
