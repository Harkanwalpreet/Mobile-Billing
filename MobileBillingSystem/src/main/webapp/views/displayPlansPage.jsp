<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Plans</title>
</head>
<body bgcolor="#ffe4e1">
	<div align="center">

		<h2>Plans:</h2>
		<table border="1px solid black" cellpadding="10">
			<tr>
				<c:forEach items="${plans}" var="item">
					<td>
						<table>
							<tr>
								<td>Plan ID:</td>
								<td><c:out value="${item.planID}" /></td>
							</tr>
							<tr>
								<td>monthly rental:</td>
								<td><c:out value="${item.monthlyRental}" /></td>
							</tr>
							<tr>
								<td>Free local calls:</td>
								<td><c:out value="${item.freeLocalCalls}" /></td>
							</tr>
							<tr>
								<td>Free STD calls:</td>
								<td><c:out value="${item.freeStdCalls}" /></td>
							</tr>
							<tr>
								<td>Free local SMS:</td>
								<td><c:out value="${item.freeLocalSMS}" /></td>
							</tr>
							<tr>
								<td>Free STD SMS:</td>
								<td><c:out value="${item.freeStdSMS}" /></td>
							</tr>
							<tr>
								<td>Free internet data units:</td>
								<td><c:out value="${item.freeInternetDataUsageUnits}" /></td>
							</tr>
							<tr>
								<td>local call rate:</td>
								<td><c:out value="${item.localCallRate}" /></td>
							</tr>
							<tr>
								<td>STD call rate:</td>
								<td><c:out value="${item.stdCallRate}" /></td>
							</tr>
							<tr>
								<td>Local SMS rate:</td>
								<td><c:out value="${item.localSMSRate}" /></td>
							</tr>
							<tr>
								<td>STD SMS rate:</td>
								<td><c:out value="${item.stdSMSRate}" /></td>
							</tr>
							<tr>
								<td>Internet data usage rate:</td>
								<td><c:out value="${item.internetDataUsageRate}" /></td>
							</tr>
							<tr>
								<td>Plan circle:</td>
								<td><c:out value="${item.planCircle}" /></td>
							</tr>
							<tr>
								<td>Plan name:</td>
								<td><c:out value="${item.planName}" /></td>
							</tr>
						</table>
					</td>
				</c:forEach>
			</tr>
		</table>



		<table>
			<tr>
				<td><form:form action="addPlanToAccount" method="post"
						modelAttribute="customer">
						<table>
							<tr>
								<th><font size="5">Open Account:</font></th>
							</tr>
							<tr>
								<td>Customer ID:</td>
								<td><form:input path="customerID" size="20" /></td>
								<td><form:errors path="customerID" cssClass="error" /></td>
							</tr>
							<tr>
								<td>Plan ID:</td>
								<td><input type="number" name="planID" /></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
							</tr>

							<tr>
								<td></td>
								<tr></tr>
							<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
							<tr>
								<td colspan="2" align="center"><input type="submit" value="Submit" />
								</td>
							</tr>
							
						
						</table>
					</form:form></td>

				<td><form:form action="editPlan" method="post">
						<table>
							<tr>
								<th><font size="5">Change Plan:</font></th>
							</tr>
							<tr>
								<td>CustomerID</td>
								<td><input type="number" name="customerID" /></td>
							</tr>
							<tr>
								<td>MobileNo</td>
								<td><input type="number" name="mobileNo" /></td>
							</tr>
							<tr>
								<td>PlanID</td>
								<td><input type="number" name="planID" /></td>
							</tr>
							<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
							<tr>
								<td colspan="2" align="center"><input type="submit"
									name="OK" /></td>
							</tr>
						</table>
					</form:form></td>
			</tr>
		</table>

		<br> <br> <font color="green">${successMessage}</font> <font
			color="red">${errorMessage}</font> <br> <br>
	</div>
</body>
</html>