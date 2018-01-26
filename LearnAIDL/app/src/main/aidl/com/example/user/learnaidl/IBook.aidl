// IBook.aidl
package com.example.user.learnaidl;

// Declare any non-default types here with import statements

interface IBook {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String queryBook(int bookNo);  
}
