package controller;

import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/*")
public class UserController extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // 用户列表页面
            listUsers(request, response);
        } else if (pathInfo.equals("/add")) {
            // 添加用户页面
            showAddForm(request, response);
        } else if (pathInfo.equals("/edit")) {
            // 编辑用户页面
            showEditForm(request, response);
        } else if (pathInfo.equals("/delete")) {
            // 删除用户
            deleteUser(request, response);
        } else if (pathInfo.equals("/status")) {
            // 更新用户状态
            updateStatus(request, response);
        } else if (pathInfo.equals("/password")) {
            // 修改密码页面
            showPasswordForm(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // 添加用户
            addUser(request, response);
        } else if (pathInfo.equals("/edit")) {
            // 更新用户
            updateUser(request, response);
        } else if (pathInfo.equals("/password")) {
            // 修改密码
            updatePassword(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 检查权限
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || !userService.hasPermission(currentUser.getId(), "admin")) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/user-add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                User user = userService.findById(id);
                if (user != null) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/user-edit.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // 忽略
            }
        }
        response.sendRedirect(request.getContextPath() + "/user/");
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (username != null && password != null && role != null) {
            User user = new User(username, password, role);
            if (userService.addUser(user)) {
                request.setAttribute("message", "用户添加成功");
            } else {
                request.setAttribute("error", "用户添加失败，用户名可能已存在");
            }
        } else {
            request.setAttribute("error", "请填写完整信息");
        }

        response.sendRedirect(request.getContextPath() + "/user/");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String username = request.getParameter("username");
        String role = request.getParameter("role");
        String statusStr = request.getParameter("status");

        if (idStr != null && username != null && role != null) {
            try {
                int id = Integer.parseInt(idStr);
                int status = statusStr != null ? Integer.parseInt(statusStr) : 1;

                User user = userService.findById(id);
                if (user != null) {
                    user.setUsername(username);
                    user.setRole(role);
                    user.setStatus(status);

                    if (userService.updateUser(user)) {
                        request.setAttribute("message", "用户更新成功");
                    } else {
                        request.setAttribute("error", "用户更新失败");
                    }
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/user/");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                if (userService.deleteUser(id)) {
                    request.setAttribute("message", "用户删除成功");
                } else {
                    request.setAttribute("error", "用户删除失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/user/");
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String statusStr = request.getParameter("status");

        if (idStr != null && statusStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                int status = Integer.parseInt(statusStr);

                if (userService.updateUserStatus(id, status)) {
                    request.setAttribute("message", "状态更新成功");
                } else {
                    request.setAttribute("error", "状态更新失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/user/");
    }

    private void showPasswordForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/user-password.jsp").forward(request, response);
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (oldPassword != null && newPassword != null && confirmPassword != null) {
            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("error", "新密码与确认密码不一致");
            } else if (userService.updatePassword(currentUser.getId(), oldPassword, newPassword)) {
                request.setAttribute("message", "密码修改成功");
            } else {
                request.setAttribute("error", "密码修改失败，请检查原密码");
            }
        } else {
            request.setAttribute("error", "请填写完整信息");
        }

        request.getRequestDispatcher("/user-password.jsp").forward(request, response);
    }
}