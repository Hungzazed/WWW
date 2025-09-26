<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>IUH Bookstore</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-white font-sans">
<div class="max-w-7xl mx-auto px-4">
    <header class="flex items-center justify-between py-6 border-b border-gray-300">
        <h1 class="text-3xl font-bold text-gray-800">IUH BOOKSTORE</h1>
        <nav class="space-x-4">
            <a href="#" class="text-gray-700 hover:text-blue-600">HOME</a>
            <a href="#" class="text-gray-700 hover:text-blue-600">EXAMPLES</a>
            <a href="#" class="text-gray-700 hover:text-blue-600">SERVICES</a>
            <a href="#" class="text-gray-700 hover:text-blue-600">PRODUCTS</a>
            <a href="#" class="text-gray-700 hover:text-blue-600">CONTACT</a>
        </nav>
    </header>


    <div class="flex mt-6">
        <!-- Sidebar -->
        <form action="${pageContext.request.contextPath}/books" method="get" class="mb-6">
            <h2 class="text-xl font-semibold text-gray-800 mb-2">SEARCH SITE</h2>
            <input
                    type="text"
                    name="search"
                    value="${param.search}"
                    placeholder="Search by book title..."
                    class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300 mb-4"
            >

            <label for="priceFilter" class="block mb-1 text-gray-700 font-semibold">Filter by Price:</label>
            <select name="priceFilter" id="priceFilter" class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300 mb-4">
                <option value="">All prices</option>
                <option value="under50k" ${param.priceFilter == 'under50k' ? 'selected' : ''}>Duoi 50,000</option>
                <option value="50kto100k" ${param.priceFilter == '50kto100k' ? 'selected' : ''}>50,000 - 100,000</option>
                <option value="above100k" ${param.priceFilter == 'above100k' ? 'selected' : ''}>Tren 100,000</option>
            </select>

            <button type="submit" class="w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700">Search</button>
        </form>
        <!-- Main Content -->
        <main class="w-3/4 pl-6">
            <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                <c:forEach items="${books}" var="b">
                    <div class="border border-gray-300 p-4 rounded-md bg-white text-center shadow hover:shadow-lg transition duration-300">
                        <h3 class="text-md font-semibold text-gray-800 mb-2">${b.title}</h3>
                        <img src="images/${b.imgUrl}" alt="${b.title}" class="w-full h-60 object-contain mx-auto mb-4">
                        <p class="text-sm text-gray-700">Price: <strong>${b.price}</strong></p>
                        <p class="text-sm text-gray-700">Quantity: <strong>${b.quantity}</strong></p>
                        <form action="${pageContext.request.contextPath}/bookcart" method="post" class="mt-4 space-y-2">
                            <input type="hidden" name="id" value="${b.id}">
                            <input type="hidden" name="price" value="${b.price}">
                            <input type="hidden" name="title" value="${b.title}">
                            <input type="hidden" name="author" value="${b.author}">
                            <input type="hidden" name="action" value="add">

                            <div class="flex flex-col space-y-2 mt-2">
                                <input type="submit" value="Add to cart" class="bg-blue-600 text-white py-1 rounded hover:bg-blue-700 cursor-pointer">
                                <a href="${pageContext.request.contextPath}/books?id=${b.id}" class="block bg-gray-200 text-gray-800 py-1 rounded hover:bg-gray-300">Product details</a>
                            </div>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </main>
    </div>
    <c:if test="${not empty sessionScope.message}">
        <script>
            alert("${sessionScope.message}");
        </script>
        <c:remove var="message" scope="session"/>
    </c:if>

</div>
</body>
</html>
