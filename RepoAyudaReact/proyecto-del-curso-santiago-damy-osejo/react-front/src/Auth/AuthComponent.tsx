import React, { type JSX } from "react";
import { Navigate, Outlet, useLocation } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

interface JwtPayload {
  exp: number;
  sub?: string;

}

export default function AuthComponent(): JSX.Element {
  const location = useLocation();
  const token = localStorage.getItem("token");

    let decodedToken: JwtPayload | null = null;

  if (token) {
    try {
      decodedToken = jwtDecode<JwtPayload>(token);
      const now = Date.now() / 1000; // current time in seconds

      if (decodedToken.exp < now) {
        // Token expired
        localStorage.removeItem("token");
        return <Navigate to="/login" replace state={{ from: location }} />;
      }

      console.log("Decoded Token:", decodedToken);

      return <Outlet context={{ user: decodedToken }} />;
    } catch (e) {
      console.error("Invalid token", e);
      localStorage.removeItem("token");
      return <Navigate to="/login" replace state={{ from: location }} />;
    }
  }

  if (location.pathname !== "/" && location.pathname !== "/login") {
    return <Navigate to="/login" replace state={{ from: location }} />;
  }

  return (
  <>
    <Outlet context={{ user: decodedToken }} />
  </>
);
}
