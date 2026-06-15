/*
 * rubber_boot_seal_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:32 by COMSOL 6.3.0.290. */
public class rubber_boot_seal_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 100, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 20, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 100, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 100, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 20, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 110, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 35, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 110, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 45, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb2", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 110, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 45, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 110, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 52, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 90, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 55, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("point1", new int[]{90, 55});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("point2", new int[]{90, 58});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("angle1", -55);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ca1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb3", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", 90, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", 58, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", 110, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", 65, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", 110, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", 73, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb4", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb4").setIndex("p", 110, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb4").setIndex("p", 73, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb4").setIndex("p", 110, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb4").setIndex("p", 80, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb4").setIndex("p", 76, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb4").setIndex("p", 85, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2").set("point1", new int[]{76, 85});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2").set("point2", new int[]{76, 88});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2").set("angle1", -55);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca2").set("clockwise", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ca2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").selection("input")
         .set("ca2", "qb3", "qb4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").set("displx", "-14 -28");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").set("disply", "30 60");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb5", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb5").setIndex("p", 48, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb5").setIndex("p", 148, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb5").setIndex("p", 58, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb5").setIndex("p", 150, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb5").setIndex("p", 58, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb5").setIndex("p", 164, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb6", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", 58, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", 164, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", 58, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", 174, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", 48, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", 174, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb7", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", 48, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", 174, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", 30, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", 174, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", 30, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", 195, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", 30, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", 195, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", 30, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").setIndex("table", 225, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("ca1", "ca2", "copy1", "pol1", "pol2", "qb1", "qb2", "qb3", "qb4", "qb5", "qb6", "qb7");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("thi1").set("upthick", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("thi1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("thi1", 21, 28, 36, 37);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("point")
         .set("fil1", 5, 6, 9, 10, 13, 14, 15, 16, 17, 18, 19, 20, 25, 26, 27, 28, 37);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", 1.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{5, 5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{27, 220});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex1").set("fil2", 50);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex2").set("fil2", 53);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls2", "ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").selection("vertex1")
         .set("fil2", new int[]{51});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").selection("vertex2")
         .set("fil2", new int[]{54});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls3", "ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3").selection("vertex1")
         .set("fil2", new int[]{58});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3").selection("vertex2")
         .set("fil2", new int[]{61});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls4", "ls3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls4").selection("vertex1")
         .set("fil2", new int[]{57});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls4").selection("vertex2")
         .set("fil2", new int[]{60});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls5", "ls4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls5").selection("vertex1")
         .set("fil2", new int[]{44});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls5").selection("vertex2")
         .set("fil2", new int[]{42});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls6", "ls5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls6").selection("vertex1")
         .set("fil2", new int[]{40});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls6").selection("vertex2")
         .set("fil2", new int[]{38});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls7", "ls6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls7").selection("vertex1")
         .set("fil2", new int[]{39});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls7").selection("vertex2")
         .set("fil2", new int[]{37});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls7");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls8", "ls7");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls8").selection("vertex1")
         .set("fil2", new int[]{41});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls8").selection("vertex2")
         .set("fil2", new int[]{43});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls8");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls9", "ls8");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls9").selection("vertex1")
         .set("fil2", new int[]{56});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls9").selection("vertex2")
         .set("fil2", new int[]{62});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls9");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls10", "ls9");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls10").selection("vertex1")
         .set("fil2", new int[]{55});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls10").selection("vertex2")
         .set("fil2", new int[]{59});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls10");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls11", "ls10");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls11").selection("vertex1")
         .set("fil2", new int[]{32});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls11").selection("vertex2")
         .set("fil2", new int[]{30});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls11");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls12", "ls11");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls12").selection("vertex1")
         .set("fil2", new int[]{28});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls12").selection("vertex2")
         .set("fil2", new int[]{26});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls12");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls13", "ls12");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls13").selection("vertex1")
         .set("fil2", new int[]{27});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls13").selection("vertex2")
         .set("fil2", new int[]{25});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls13");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls14", "ls13");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls14").selection("vertex1")
         .set("fil2", new int[]{29});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls14").selection("vertex2")
         .set("fil2", new int[]{31});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls14");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls15", "ls14");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls15").selection("vertex1")
         .set("fil2", new int[]{46});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls15").selection("vertex2")
         .set("fil2", new int[]{48});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls15");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls16", "ls15");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls16").selection("vertex1")
         .set("fil2", new int[]{45});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls16").selection("vertex2")
         .set("fil2", new int[]{47});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls16");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls17", "ls16");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls17").selection("vertex1")
         .set("fil2", new int[]{24});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls17").selection("vertex2")
         .set("fil2", new int[]{22});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls17");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls18", "ls17");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls18").selection("vertex1")
         .set("fil2", new int[]{20});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls18").selection("vertex2")
         .set("fil2", new int[]{18});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls18");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls19", "ls18");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls19").selection("vertex1")
         .set("fil2", new int[]{19});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls19").selection("vertex2")
         .set("fil2", new int[]{17});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls19");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls20", "ls19");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls20").selection("vertex1")
         .set("fil2", new int[]{21});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls20").selection("vertex2")
         .set("fil2", new int[]{23});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls20");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls21", "ls20");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls21").selection("vertex1")
         .set("fil2", new int[]{34});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls21").selection("vertex2")
         .set("fil2", new int[]{36});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls21");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls22", "ls21");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls22").selection("vertex1")
         .set("fil2", new int[]{33});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls22").selection("vertex2")
         .set("fil2", new int[]{35});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls22");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls23", "ls22");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls23").selection("vertex1")
         .set("fil2", new int[]{14});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls23").selection("vertex2")
         .set("fil2", new int[]{11});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls23");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls24", "ls23");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls24").selection("vertex1")
         .set("fil2", new int[]{10});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls24").selection("vertex2")
         .set("fil2", new int[]{6});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls24");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls25", "ls24");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls25").selection("vertex1")
         .set("fil2", new int[]{7});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls25").selection("vertex2")
         .set("fil2", new int[]{5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls25");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls26", "ls25");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls26").selection("vertex1")
         .set("fil2", new int[]{13});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls26").selection("vertex2")
         .set("fil2", new int[]{12});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls26");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls27", "ls26");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls27").selection("vertex1")
         .set("fil2", new int[]{15});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls27").selection("vertex2")
         .set("fil2", new int[]{16});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls27");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls28", "ls27");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls28").selection("vertex1")
         .set("fil2", new int[]{8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls28").selection("vertex2")
         .set("fil2", new int[]{9});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls28");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("ls29", "ls28");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls29").selection("vertex1")
         .set("fil2", new int[]{1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls29").selection("vertex2")
         .set("fil2", new int[]{3});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls29");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").placeAfter("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls3");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls4");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls5");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls6");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls7");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls8");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls9");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls10");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls11");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls12");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls13");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls14");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls15");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls16");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls17");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls18");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls19");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls20");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls21");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls22");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls23");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls24");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls25");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls26");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls27");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls28");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").add("ls29");
    model.component("comp1").geom("geom1").feature("wp1").geom().nodeGroup("grp1").label("\u5206\u5272\u5bf9\u8c61");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 90);
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("rev1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "(60)/2-3");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 275);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{0, 0, -10});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", 3, 0);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("cyl1", 1, 3, 4);
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("igf1", "IgnoreFaces");
    model.component("comp1").geom("geom1").feature("igf1").selection("input")
         .set("fin", 12, 15, 25, 38, 44, 47, 62, 68, 71, 81, 84, 101, 104, 162, 165, 182, 185, 201, 204, 210, 225, 228, 234, 244, 251, 255);
    model.component("comp1").geom("geom1").run("igf1");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input")
         .set("igf1", 4, 9, 23, 35, 51, 73, 74, 85, 90, 109, 132, 144, 153, 154);
    model.component("comp1").geom("geom1").run("mcf1");

    model.title(null);

    model.description("");

    model.label("rubber_boot_seal_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
