import { createBrowserRouter } from "react-router-dom";

import AuthComponent from "../Auth/AuthComponent";
/* Profile management */
import ProfilePage from "../pages/ProfilePage";

import NewProductPage from "../pages/NewProductPage";         // /product/new
import ProductDetailPage from "../pages/ProductDetailPage";   // /product/:productId
/* Search and favorites */
import SearchPage from "../pages/SearchPage";                 // /search
/* Chat */
import ChatPage from "../pages/ChatPage";                     // /chat
/* Notifications */
import NotificationsPage from "../pages/NotificationsPage";   // /notifications
/* Purchase */
import PurchasePage from "../pages/PurchasePage";             // /purchase/:productId
/* Home */
import HomePage from "../pages/HomePage";                     // /home
/* Login */
import Login from "../pages/LogIn";                           // /login
/* Register */
import Register from "../pages/Register";                     // /register

const router = createBrowserRouter(
  [
    {
      path: "/",
      Component: AuthComponent,
      children: [
        { index: true, Component: HomePage },
        {
          path: "profile",
          children: [{ index: true, Component: ProfilePage }],
        },
        {
          path: "product",
          children: [
            { path: "new", Component: NewProductPage },
            { path: ":productId", Component: ProductDetailPage },
          ],
        },
        { path: "search", Component: SearchPage },
        { path: "chat", Component: ChatPage },
        { path: "notifications", Component: NotificationsPage },
        { path: "purchase/:productId", Component: PurchasePage },
      ],
    },
    { path: "/login", Component: Login },
    { path: "/register", Component: Register },

  ],
  { basename: "/" }
);

export default router;
