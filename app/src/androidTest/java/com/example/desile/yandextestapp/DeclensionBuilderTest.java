package com.example.desile.yandextestapp;

import junit.framework.TestCase;

public class DeclensionBuilderTest extends TestCase {

    public void testBuild() throws Exception {
        assertEquals(DeclensionBuilder.build(DeclensionBuilder.albumCase,1),"1 альбом");
        assertEquals(DeclensionBuilder.build(DeclensionBuilder.albumCase,2),"2 альбома");
        assertEquals(DeclensionBuilder.build(DeclensionBuilder.albumCase,5),"5 альбомов");
        assertEquals(DeclensionBuilder.build(DeclensionBuilder.albumCase,10),"10 альбомов");
        assertEquals(DeclensionBuilder.build(DeclensionBuilder.albumCase,11),"11 альбомов");
        assertEquals(DeclensionBuilder.build(DeclensionBuilder.albumCase,21),"21 альбом");
        assertEquals(DeclensionBuilder.build(DeclensionBuilder.albumCase,100),"100 альбомов");
        assertEquals(DeclensionBuilder.build(DeclensionBuilder.albumCase,1011),"1011 альбомов");
        assertEquals(DeclensionBuilder.build(DeclensionBuilder.albumCase,1237),"1237 альбомов");
        assertEquals(DeclensionBuilder.build(DeclensionBuilder.albumCase,1234),"1234 альбома");
    }
}