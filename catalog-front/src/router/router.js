import Main from "@/pages/Main";
import {createRouter, createWebHistory} from "vue-router";
import AuthorsPage from "@/pages/AuthorsPage";
import AuthorPage from "@/pages/AuthorPage";
import BooksPage from "@/pages/BooksPage";
import BookPage from "@/pages/BookPage";
import AddBookPage from "@/pages/AddBookPage";

import About from "@/pages/PageAbout";

const routes = [
    {
        path: '/',
        component: Main
    },
    {
        path: '/authors',
        component: AuthorsPage
    },
    {
        path: '/authors/:id',
        component: AuthorPage
    },
    {
        path: '/about',
        component: About
    },
    {
        path: '/books',
        component: BooksPage
    },
   
    {
        path: '/books/:id',
        component: BookPage
    },
    {
        path: '/addBook',
        component: AddBookPage
    }

]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;
// export default{};
