import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import cors from "cors";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome'

createApp(App)
    .use(router)
    .use(store)
    .use(cors)
    .component("font-awesome-icon", FontAwesomeIcon)
    .mount("#app");
