<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="_header.jsp"/>


<div class="main2">
    <h1>Tickets</h1>
    <c:if test="${!groups.isEmpty()}">
        <form class="newtick" action="/addticket">
            <input class="tickbut" type="submit" value="Add new ticket" />
        </form>
    </c:if>
    <form:form action="/tickets/filter" method="get">
        <table class="filtert">
            <tr>
                <td class="filter">
        <label for="group" class="filter">Filter by group</label>
        <select name="group" id="group">
            <option value="0">All groups</option>
            <c:forEach items="${groups}" var="group">
                <option value="${group.id}">${group.groupName}</option>
            </c:forEach>
        </select>
                </td>
                <td class="filter" >
                <label for="status" class="filter">Status</label>
                <select name="status" id="status">
                    <option value="active">Active</option>
                    <option value="finished">Finished</option>
                </select>
                </td>
                <td class="filter">
                    <label for="assignee" class="filter">Assignees:</label>
                    <select name="assignee" id="assignee">
                        <option value="all">Show all</option>
                        <option value="user">Assigned to me</option>
                        <option value="unassigned">Unassigned</option>
                        <option value="userAssigner">Created by me</option>
                    </select>
                </td>
                <td>
                    <input type="submit" value="Filter">
                </td>
            </tr>
            <tr>

            </tr>

        </table>
    </form:form>
    <table>
        <tr>
            <th>Priority</th>
            <th>Task</th>
            <th>Deadline</th>
            <th>Group</th>
            <th>Assigner</th>
            <th>Assignee</th>
        </tr>

            <c:forEach items="${ticketList}" var="ticket">
                <tr>
                    <td>${ticket.priority}</td>
                    <td><a href="/ticket/${ticket.id}">${ticket.name}</a></td>
                    <td>${ticket.deadline}</td>
                    <td>${ticket.group.groupName}</td>
                    <td>${ticket.assigner.name}</td>
                    <td><c:if test="${ticket.assignees.size()==0}">
                        Unassigned
                    </c:if>
                        <c:if test="${ticket.assignees.size()>0}">
                            ${ticket.assignees[0].name}
                        </c:if>
                        <c:if test="${ticket.assignees.size()>1}">
                        and ${ticket.assignees.size()-1} others </td>
                    </c:if>
                </tr>
            </c:forEach>

    </table>
</div>
<jsp:include page="_footer.jsp"/>