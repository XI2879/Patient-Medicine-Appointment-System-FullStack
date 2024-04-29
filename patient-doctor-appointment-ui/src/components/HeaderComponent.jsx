import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { isUserLoggedIn, logout } from "../services/AuthService";

const HeaderComponent = () => {
  const isAuth = isUserLoggedIn();
  const navigate = useNavigate();

  function handlLogout() {
    logout();
    navigate("/login");
  }
  return (
    <div>
      <header className="header">
        <nav className="navbar navbar-expand-md navbar-dark bg-primary">
          <div>
            <a href="http://localhost:3000" className="navbar-brand">
              Patient Medicine and Appointment Application
            </a>
          </div>
          <div className="collapse navbar-collapse">
            <ul className="navbar-nav">
              {!isAuth && (
                <li className="nav-item">
                  <NavLink to="/login" className="nav-link">
                    Login
                  </NavLink>
                </li>
              )}
              {!isAuth && (
                <li className="nav-item">
                  <NavLink to="/patient-register" className="nav-link">
                    PatientRegister
                  </NavLink>
                </li>
              )}
              {!isAuth && (
                <li className="nav-item">
                  <NavLink to="/doctor-register" className="nav-link">
                    DoctorRegister
                  </NavLink>
                </li>
              )}
              
              {isAuth && (
                <li className="nav-item">
                  <NavLink
                    to="/login"
                    className="nav-link"
                    onClick={handlLogout}
                  >
                    Logout
                  </NavLink>
                </li>
              )}
            </ul>
          </div>
        </nav>
      </header>
    </div>
  );
};

export default HeaderComponent;
