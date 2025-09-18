<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
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

    <!-- Content -->
    <div class="flex mt-6">
        <!-- Sidebar -->
        <aside class="w-1/4 pr-6 border-r border-gray-300">
            <h2 class="text-xl font-semibold text-gray-800 mb-4">ABOUT US</h2>
            <p class="text-sm text-gray-600 mb-6">About us information will be here...
                <a href="#" class="text-blue-500">Read More »</a>
            </p>

            <h2 class="text-xl font-semibold text-gray-800 mb-2">SEARCH SITE</h2>
            <input type="text" placeholder="Search..." class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300">

            <p class="mt-4 text-sm text-blue-600">Shopping cart (${bookcart.items.size()})</p>
        </aside>

        <!-- Checkout Form -->
        <main class="w-3/4 pl-6">
            <h2 class="text-xl font-semibold text-gray-800 mb-6">Checkout - Already registered...?</h2>

            <form action="${pageContext.request.contextPath}/checkouts" method="post" class="bg-white border border-gray-300 p-6 rounded-md space-y-4">
                <div class="flex items-center">
                    <label class="w-32 text-gray-700">Fullname:</label>
                    <input type="text" name="fullname" required class="flex-1 border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-1 focus:ring-blue-500">
                </div>

                <div class="flex items-center">
                    <label class="w-32 text-gray-700">Shipping address:</label>
                    <input type="text" name="address" required class="flex-1 border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-1 focus:ring-blue-500">
                </div>

                <div class="flex items-center">
                    <label class="w-32 text-gray-700">Total price:</label>
                    <input type="text" name="total" readonly value="${bookcart.total}" class="flex-1 border border-gray-300 rounded px-3 py-2 bg-gray-100">
                </div>

                <div class="flex items-start">
                    <label class="w-32 text-gray-700 pt-1">Payment method:</label>
                    <div class="flex-1 space-x-4 text-gray-700">
                        <label><input type="radio" name="payment" value="Paypal" required> Paypal</label>
                        <label><input type="radio" name="payment" value="ATM" required> ATM</label>
                        <label><input type="radio" name="payment" value="Visa/Master" required> Visa/Master card</label>
                    </div>
                </div>

                <div class="flex space-x-4 pt-4">
                    <button type="submit" class="bg-gray-800 text-white px-4 py-2 rounded hover:bg-black">Save</button>
                    <a href="${pageContext.request.contextPath}/books" class="bg-gray-300 text-gray-800 px-4 py-2 rounded hover:bg-gray-400">Cancel</a>
                </div>
            </form>
        </main>
    </div>
</div>
</body>
</html>
