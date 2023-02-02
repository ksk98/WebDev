import { createWebHistory, createRouter } from "vue-router";
import Home from "./components/Home.vue";
import Login from "./components/Login.vue";
import Register from "./components/Register.vue";
import CreateGallery from "@/components/CreateGallery";
import GalleryView from "@/components/GalleryView";
import GalleryRename from "@/components/GalleryRename";
import GalleryImageUpload from "@/components/GalleryImageUpload";
// lazy-loaded
// const Profile = () => import("./components/Profile.vue")
// const BoardAdmin = () => import("./components/BoardAdmin.vue")
// const BoardModerator = () => import("./components/BoardModerator.vue")
// const BoardUser = () => import("./components/BoardUser.vue")
const Galleries = () => import("./components/Gallery")

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/home",
    component: Home,
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/register",
    component: Register,
  },
  {
    path: "/galleries",
    component: Galleries,
  },
  {
    path: "/createGallery",
    component: CreateGallery
  },
  {
    path: "/gallery/:id",
    component: GalleryView
  },
  {
    path: "/gallery/:id/rename",
    component: GalleryRename
  },
  {
    path: "/gallery/:id/uploadImages",
    component: GalleryImageUpload
  }
  // {
  //   path: "/profile",
  //   name: "profile",
  //   // lazy-loaded
  //   component: Profile,
  // },
  // {
  //   path: "/admin",
  //   name: "admin",
  //   // lazy-loaded
  //   component: BoardAdmin,
  // },
  // {
  //   path: "/mod",
  //   name: "moderator",
  //   // lazy-loaded
  //   component: BoardModerator,
  // },
  // {
  //   path: "/user",
  //   name: "user",
  //   // lazy-loaded
  //   component: BoardUser,
  // },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register', '/home'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/login');
  } else {
    next();
  }
});

export default router;