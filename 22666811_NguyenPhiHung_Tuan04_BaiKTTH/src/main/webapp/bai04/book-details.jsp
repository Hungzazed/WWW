<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Detail</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-white font-sans">
<div class="max-w-7xl mx-auto px-4">
    <!-- Header -->
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

    <!-- Sidebar + Content -->
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
        <!-- Book Detail -->
        <main class="w-3/4 pl-6">
            <c:if test="${not empty book}">
                <p class="text-md text-gray-800 mb-4">
                    Product details: <span class="font-semibold">${book.title}</span> - Tác giả <span class="font-semibold">${book.author}</span>
                </p>

                <div class="flex space-x-6 items-start">
                    <img src="${pageContext.request.contextPath}/images/${book.imgUrl}" alt="${book.title}" class="w-48 object-cover shadow border border-gray-300">

                    <div class="text-gray-700 space-y-2 text-sm">
                        <p><span class="font-semibold">Price (VNĐ):</span> ${book.price}</p>
                        <p><span class="font-semibold">Quantity:</span> ${book.quantity}</p>
                    </div>
                </div>
            </c:if>

            <div class="mt-6">
                <a href="${pageContext.request.contextPath}/books" class="text-blue-600 hover:underline">
                    Back to Product List
                </a>
            </div>
        </main>
    </div>
</div>
</body>
</html>
