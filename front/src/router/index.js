import Vue from 'vue'
import VueRouter from 'vue-router'
import AppIndex from '@/views/AppIndex'
import NoticeList from '@/components/notice/NoticeList';
import NoticeView from '@/components/notice/NoticeView';

import BoardList from '@/components/board/BoardList';
import BoardView from '@/components/board/BoardView';

import UserInfo from '@/components/user/UserInfo';

import BoardWrite from '@/components/global/BoardWrite';
import AppMapView from '@/components/map/AppMapView';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: AppIndex
  },
  //notice
  {
    path: '/notice/list',
    name: 'NoticeList',
    component: NoticeList
  },
  {
    path: '/noitce/view',
    name: 'NoticeView',
    component: NoticeView
  },
  // user  
  {
    path: '/user/my-page',
    name : 'MyPage',
    component : UserInfo
  },
  //board
  {
    path: '/board/list',
    name: 'BoardList',
    component: BoardList
  },
  {
    path: '/board/view',
    name: 'BoardView',
    component: BoardView
  },
  // global 
  {
    path : '/board/write',
    name : 'BoardWrite',
    component : BoardWrite
  },
  //map
  {
    path : '/map',
    name : 'MapView',
    component : AppMapView
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
