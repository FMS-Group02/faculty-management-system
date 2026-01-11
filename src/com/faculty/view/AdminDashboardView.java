package com.faculty.view;

import com.faculty.model.Session;
import com.faculty.view.panels.*;
import javax.swing.*;

/**
 * Admin implementation of the Dashboard.
 * Inherits the geometric layout and navigation logic from DashboardFrame.
 */
public class AdminDashboardView extends DashboardFrame {

    /**
     * Constructor for AdminDashboardView.
     * @param session The current user session containing username and role.
     */
    public AdminDashboardView(Session session) {
        // FIXED: Passes the session and the window title to the parent DashboardFrame constructor.
        // This resolves the "constructor cannot be applied to given types" error.
        super(session, "Admin Dashboard - Faculty Management System");
    }


    @Override
    protected void buildPages() {
        // addNavItem handles panel registration, CardLayout integration,
        // and SidebarButton creation automatically.
        addNavItem("overview", "Overview", new AdminOverviewPanel());
        addNavItem("users", "Users", new UsersPanel());
        addNavItem("students", "Students", new StudentsPanel());
        addNavItem("lecturers", "Lecturers", new LecturersPanel());
        addNavItem("courses", "Courses", new CoursesPanel());
        addNavItem("departments", "Departments", new DepartmentsPanel());
        addNavItem("degrees", "Degrees", new DegreesPanel());
        addNavItem("enrollments", "Enrollments", new EnrollmentsPanel());
        addNavItem("timetable", "Timetable", new TimetablePanel());
    }
}