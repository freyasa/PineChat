import {createWebHistory, createRouter} from "vue-router";
import Chat from "@/components/Chat";
import Friend from "@/components/Friend";
import App from "@/App";
// import About from "@/views/About.vue";

const routes = [
    {
        path: "/chat",
        name: "chat",
        component: Chat,
    },
    {
        path: "/friend",
        name: "friend",
        component: Friend,
    },
    {
        path: "/app",
        name: "app",
        component: App,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default {
    router,
}