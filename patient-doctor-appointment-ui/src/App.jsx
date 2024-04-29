
import "./App.css";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from "./components/FooterComponent";
import { isUserLoggedIn } from "./services/AuthService";
import DoctorRegister from "./components/DoctorRegister";
import DualLogin from "./components/DualLogin";
import PatientRegister from "./components/PatientRegister";
import Homepage from "./components/Homepage";
import LoggedIn from "./components/LoggedIn";
import DoctorsList from "./components/doctors/DoctorsList";
import BookingForm from "./components/doctors/BookingForm";
import PatientsList from "./components/patients/PatientsList";
import MedicationForm from "./components/medications/MedicationForm";

function App() {
  function AuthenticatedRoute({ children }) {
    const isAuth = isUserLoggedIn();

    if (isAuth) {
      return children;
    }

    return <Navigate to="/" />;
  }

  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            <>
              <HeaderComponent />
              <Homepage />
              <FooterComponent />
            </>
          }
        ></Route>
        <Route path="/login" element={<DualLogin />}></Route>
        <Route path="/patient-register" element={<PatientRegister />}></Route>
        <Route path="/doctor-register" element={<DoctorRegister />}></Route>
        <Route path="/logged-in" element={<AuthenticatedRoute><LoggedIn /></AuthenticatedRoute>}></Route>
        <Route path="/doctors" element={<AuthenticatedRoute><DoctorsList /></AuthenticatedRoute>}></Route>
        <Route path="/patients" element={<AuthenticatedRoute><PatientsList /></AuthenticatedRoute>}></Route>
        <Route path="/doctors/book" element={<AuthenticatedRoute><BookingForm /></AuthenticatedRoute>}></Route>
        <Route path="/doctors/add-medication" element={<AuthenticatedRoute><MedicationForm></MedicationForm></AuthenticatedRoute>}></Route>
      </Routes>
    </BrowserRouter>
        
        
  );
}

export default App;


// {/* <Routes>
//           <Route path="/" element={<LoginComponent />}></Route>
//           <Route
//             path="/todos"
//             element={
//               <AuthenticatedRoute>
//                 <ListTodoComponent />
//               </AuthenticatedRoute>
//             }
//           ></Route>
//           <Route
//             path="/add-todo"
//             element={
//               <AuthenticatedRoute>
//                 <TodoComponent />
//               </AuthenticatedRoute>
//             }
//           ></Route>
//           <Route
//             path="/update-todo/:id"
//             element={
//               <AuthenticatedRoute>
//                 <TodoComponent />
//               </AuthenticatedRoute>
//             }
//           ></Route>
//           <Route path="/login" element={<LoginComponent />}></Route>
//           <Route path="/register" element={<RegisterComponent />}></Route>
//         </Routes> */}