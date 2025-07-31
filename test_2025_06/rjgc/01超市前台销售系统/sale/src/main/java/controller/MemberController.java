package controller;

import entity.User;
import entity.Member;
import entity.MemberLevelRule;
import service.MemberService;
import service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // 会员列表页面
            listMembers(request, response);
        } else if (pathInfo.equals("/add")) {
            // 添加会员页面
            showAddForm(request, response);
        } else if (pathInfo.equals("/edit")) {
            // 编辑会员页面
            showEditForm(request, response);
        } else if (pathInfo.equals("/delete")) {
            // 删除会员
            deleteMember(request, response);
        } else if (pathInfo.equals("/points")) {
            // 积分管理页面
            showPointsForm(request, response);
        } else if (pathInfo.equals("/level")) {
            // 等级管理页面
            showLevelForm(request, response);
        } else if (pathInfo.equals("/rules")) {
            // 等级规则页面
            showLevelRules(request, response);
        } else if (pathInfo.equals("/search")) {
            // 会员搜索
            searchMembers(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // 添加会员
            addMember(request, response);
        } else if (pathInfo.equals("/edit")) {
            // 更新会员
            updateMember(request, response);
        } else if (pathInfo.equals("/points")) {
            // 积分操作
            pointsOperation(request, response);
        } else if (pathInfo.equals("/level")) {
            // 等级操作
            levelOperation(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listMembers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 检查权限
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || !hasMemberPermission(currentUser)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String level = request.getParameter("level");
        String statusStr = request.getParameter("status");

        List<Member> members;
        if (level != null && !level.isEmpty()) {
            members = memberService.findByLevel(level);
        } else if (statusStr != null && !statusStr.isEmpty()) {
            try {
                int status = Integer.parseInt(statusStr);
                members = memberService.findByStatus(status);
            } catch (NumberFormatException e) {
                members = memberService.findAll();
            }
        } else {
            members = memberService.findAll();
        }

        request.setAttribute("members", members);
        request.getRequestDispatcher("/member.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/member-add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Member member = memberService.findById(id);
                if (member != null) {
                    request.setAttribute("member", member);
                    request.getRequestDispatcher("/member-edit.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // 忽略
            }
        }
        response.sendRedirect(request.getContextPath() + "/member/");
    }

    private void addMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String cardNo = request.getParameter("cardNo");
        String phone = request.getParameter("phone");
        String level = request.getParameter("level");

        if (name != null && cardNo != null) {
            Member member = new Member(name, cardNo, phone);
            if (level != null && !level.isEmpty()) {
                member.setLevel(level);
            }

            if (memberService.addMember(member)) {
                request.setAttribute("message", "会员添加成功");
            } else {
                request.setAttribute("error", "会员添加失败，卡号可能已存在");
            }
        } else {
            request.setAttribute("error", "请填写完整信息");
        }

        response.sendRedirect(request.getContextPath() + "/member/");
    }

    private void updateMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String cardNo = request.getParameter("cardNo");
        String phone = request.getParameter("phone");
        String level = request.getParameter("level");
        String statusStr = request.getParameter("status");

        if (idStr != null && name != null && cardNo != null) {
            try {
                int id = Integer.parseInt(idStr);
                int status = statusStr != null ? Integer.parseInt(statusStr) : 1;

                Member member = memberService.findById(id);
                if (member != null) {
                    member.setName(name);
                    member.setCardNo(cardNo);
                    member.setPhone(phone);
                    member.setLevel(level);
                    member.setStatus(status);

                    if (memberService.updateMember(member)) {
                        request.setAttribute("message", "会员更新成功");
                    } else {
                        request.setAttribute("error", "会员更新失败");
                    }
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/member/");
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                if (memberService.deleteMember(id)) {
                    request.setAttribute("message", "会员删除成功");
                } else {
                    request.setAttribute("error", "会员删除失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/member/");
    }

    private void showPointsForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Member member = memberService.findById(id);
                if (member != null) {
                    request.setAttribute("member", member);
                    request.getRequestDispatcher("/member-points.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // 忽略
            }
        }
        response.sendRedirect(request.getContextPath() + "/member/");
    }

    private void pointsOperation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String operation = request.getParameter("operation");
        String pointsStr = request.getParameter("points");

        if (idStr != null && operation != null && pointsStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                int points = Integer.parseInt(pointsStr);
                boolean success = false;

                Member member = memberService.findById(id);
                if (member != null) {
                    int newPoints;
                    switch (operation) {
                        case "add":
                            newPoints = member.getPoints() + points;
                            success = memberService.updatePoints(id, newPoints);
                            break;
                        case "subtract":
                            newPoints = Math.max(0, member.getPoints() - points);
                            success = memberService.updatePoints(id, newPoints);
                            break;
                        case "set":
                            success = memberService.updatePoints(id, points);
                            break;
                    }

                    // 检查升级
                    if (success) {
                        memberService.checkAndUpgradeMember(id);
                    }
                }

                if (success) {
                    request.setAttribute("message", "积分操作成功");
                } else {
                    request.setAttribute("error", "积分操作失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/member/");
    }

    private void showLevelForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Member member = memberService.findById(id);
                if (member != null) {
                    request.setAttribute("member", member);
                    request.getRequestDispatcher("/member-level.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // 忽略
            }
        }
        response.sendRedirect(request.getContextPath() + "/member/");
    }

    private void levelOperation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String level = request.getParameter("level");

        if (idStr != null && level != null) {
            try {
                int id = Integer.parseInt(idStr);

                if (memberService.updateLevel(id, level)) {
                    request.setAttribute("message", "等级更新成功");
                } else {
                    request.setAttribute("error", "等级更新失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/member/");
    }

    private void showLevelRules(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<MemberLevelRule> rules = memberService.getMemberLevelRules();
        request.setAttribute("rules", rules);
        request.getRequestDispatcher("/member-rules.jsp").forward(request, response);
    }

    private void searchMembers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String level = request.getParameter("level");

        // 这里应该实现搜索逻辑，暂时返回所有会员
        List<Member> members = memberService.findAll();

        // 简单的关键词过滤
        if (keyword != null && !keyword.isEmpty()) {
            members.removeIf(member -> !member.getName().toLowerCase().contains(keyword.toLowerCase()) &&
                    !member.getCardNo().contains(keyword) &&
                    (member.getPhone() == null || !member.getPhone().contains(keyword)));
        }

        // 等级过滤
        if (level != null && !level.isEmpty()) {
            members.removeIf(member -> !level.equals(member.getLevel()));
        }

        request.setAttribute("members", members);
        request.setAttribute("keyword", keyword);
        request.setAttribute("level", level);
        request.getRequestDispatcher("/member.jsp").forward(request, response);
    }

    private boolean hasMemberPermission(User user) {
        return "admin".equals(user.getRole()) || "member".equals(user.getRole());
    }
}