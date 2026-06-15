/*
 * full_ear_hearing_aid_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class full_ear_hearing_aid_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").geomRep("cadps");
    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").lengthUnit("mm");
    model.geom("part1").geomRep("cadps");
    model.geom("part1").designBooleans(true);
    model.geom("part1").create("imp1", "Import");
    model.geom("part1").feature("imp1").set("filename", "full_ear_hearing_aid_ha_body.mphbin");
    model.geom("part1").feature("imp1").importData();
    model.geom("part1").create("extract1", "Extract");
    model.geom("part1").feature("extract1").selection("input")
         .set("imp1", 16, 17, 18, 19, 26, 27, 32, 33, 43, 44, 53, 54, 55, 56, 65, 67, 687, 688, 1036, 1038, 1063, 1064, 1081, 1082, 1165, 1166, 1174, 1175, 1479, 1480, 1539, 1540, 1722, 1723, 1768, 1769, 1820, 1821, 2133, 2134, 2143, 2144, 2264, 2265, 2357, 2358, 2375, 2423, 2500, 2501, 2655, 2656, 2657, 2658, 2659);
    model.geom("part1").feature("extract1").set("inputhandling", "remove");
    model.geom("part1").run("extract1");
    model.geom("part1").create("cap1", "CapFaces");
    model.geom("part1").feature("cap1").set("groupadjedg", true);
    model.geom("part1").feature("cap1").selection("input")
         .add("extract1", 6, 7, 8, 9, 10, 11, 14, 15, 21, 22, 23, 24, 25, 26, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 70, 71, 72, 74, 76, 77, 111, 112, 122, 123, 135, 137, 156, 158, 163, 164, 165, 166, 181, 182);
    model.geom("part1").run("cap1");
    model.geom("part1").create("ls1", "LineSegment");
    model.geom("part1").feature("ls1").selection("vertex1").set("cap1", 114);
    model.geom("part1").feature("ls1").selection("vertex2").set("cap1", 103);
    model.geom("part1").feature().duplicate("ls2", "ls1");
    model.geom("part1").runPre("ls2");
    model.geom("part1").feature("ls2").selection("vertex1").set("cap1", new int[]{78});
    model.geom("part1").feature("ls2").selection("vertex2").set("cap1", new int[]{74});
    model.geom("part1").feature().duplicate("ls3", "ls2");
    model.geom("part1").feature("ls3").selection("vertex1").set("cap1", new int[]{29});
    model.geom("part1").feature("ls3").selection("vertex2").set("cap1", new int[]{27});
    model.geom("part1").feature().duplicate("ls4", "ls3");
    model.geom("part1").runPre("ls4");
    model.geom("part1").feature("ls4").selection("vertex1").set("cap1", new int[]{115});
    model.geom("part1").feature("ls4").selection("vertex2").set("cap1", new int[]{104});
    model.geom("part1").feature().duplicate("ls5", "ls4");
    model.geom("part1").feature("ls5").selection("vertex1").set("cap1", new int[]{117});
    model.geom("part1").feature("ls5").selection("vertex2").set("cap1", new int[]{98});
    model.geom("part1").feature().duplicate("ls6", "ls5");
    model.geom("part1").runPre("ls6");
    model.geom("part1").feature("ls6").selection("vertex1").set("cap1", new int[]{79});
    model.geom("part1").feature("ls6").selection("vertex2").set("cap1", new int[]{75});
    model.geom("part1").feature().duplicate("ls7", "ls6");
    model.geom("part1").feature("ls7").selection("vertex1").set("cap1", new int[]{26});
    model.geom("part1").feature("ls7").selection("vertex2").set("cap1", new int[]{28});
    model.geom("part1").feature().duplicate("ls8", "ls7");
    model.geom("part1").runPre("ls8");
    model.geom("part1").feature("ls8").selection("vertex1").set("cap1", new int[]{116});
    model.geom("part1").feature("ls8").selection("vertex2").set("cap1", new int[]{97});
    model.geom("part1").run("ls2");
    model.geom("part1").create("uni1", "Union");
    model.geom("part1").feature("uni1").selection("input")
         .set("cap1", "ls1", "ls2", "ls3", "ls4", "ls5", "ls6", "ls7", "ls8");
    model.geom("part1").run("uni1");
    model.geom("part1").create("cap2", "CapFaces");
    model.geom("part1").feature("cap2").set("groupadjedg", true);
    model.geom("part1").feature("cap2").selection("input").add("uni1", 99, 102, 105, 147);
    model.geom("part1").run("cap2");
    model.geom("part1").feature().duplicate("cap3", "cap2");
    model.geom("part1").feature("cap3").selection("input").add("cap2", 137, 138);
    model.geom("part1").run("cap3");
    model.geom("part1").feature().duplicate("cap4", "cap3");
    model.geom("part1").feature("cap4").selection("input").add("cap3", 141, 166);
    model.geom("part1").run("cap4");
    model.geom("part1").feature().duplicate("cap5", "cap4");
    model.geom("part1").feature("cap5").selection("input").add("cap4", 140, 147);
    model.geom("part1").run("cap5");
    model.geom("part1").feature().duplicate("cap6", "cap5");
    model.geom("part1").feature("cap6").selection("input").add("cap5", 99, 102, 106);
    model.geom("part1").run("cap6");
    model.geom("part1").create("cap7", "CapFaces");
    model.geom("part1").feature("cap7").set("groupadjedg", true);
    model.geom("part1").feature("cap7").selection("input").add("cap6", 46, 53);
    model.geom("part1").run("cap7");
    model.geom("part1").feature().duplicate("cap8", "cap7");
    model.geom("part1").feature("cap8").selection("input").add("cap7", 24, 48);
    model.geom("part1").run("cap8");
    model.geom("part1").feature().duplicate("cap9", "cap8");
    model.geom("part1").feature("cap9").selection("input").add("cap8", 51, 98);
    model.geom("part1").run("cap9");
    model.geom("part1").feature().duplicate("cap10", "cap9");
    model.geom("part1").feature("cap10").selection("input").add("cap9", 1, 252);
    model.geom("part1").run("cap10");
    model.geom("part1").create("csol1", "ConvertToSolid");
    model.geom("part1").feature("csol1").selection("input").set("cap10");
    model.geom("part1").run("csol1");
    model.geom("part1").create("wp1", "WorkPlane");
    model.geom("part1").feature("wp1").set("unite", true);
    model.geom("part1").feature("wp1").set("planetype", "vertices");
    model.geom("part1").feature("wp1").selection("vertex1").set("csol1", 68);
    model.geom("part1").feature("wp1").selection("vertex2").set("csol1", 67);
    model.geom("part1").feature("wp1").selection("vertex3").set("csol1", 10);
    model.geom("part1").feature("wp1").set("offset", 3);
    model.geom("part1").run("wp1");
    model.geom("part1").create("pard1", "PartitionDomains");
    model.geom("part1").feature("pard1").selection("domain").set("csol1", 1);
    model.geom("part1").run("pard1");
    model.geom("part1").create("del1", "Delete");
    model.geom("part1").feature("del1").selection("input").init(3);
    model.geom("part1").feature("del1").selection("input").set("pard1", 1);
    model.geom("part1").run("del1");
    model.geom("part1").create("del2", "Delete");
    model.geom("part1").feature("del2").selection("input").set("del1", 9, 10, 66, 67, 68, 69);
    model.geom("part1").run("del2");
    model.geom("part1").create("uni2", "Union");
    model.geom("part1").feature("uni2").selection("input").set("del2");
    model.geom("part1").feature("uni2").set("intbnd", false);
    model.geom("part1").run("uni2");
    model.geom("part1").create("fin", "FormUnion");
    model.geom("part1").run("fin");
    model.geom("part1").create("igv1", "IgnoreVertices");
    model.geom("part1").feature("igv1").selection("input")
         .set("fin", 19, 22, 23, 24, 25, 26, 31, 49, 50, 64, 65, 69, 70, 71, 72, 81, 84, 85, 98, 99, 100, 109, 110, 111, 117, 118);
    model.geom("part1").run("igv1");
    model.geom("part1").create("ige1", "IgnoreEdges");
    model.geom("part1").feature("ige1").selection("input").set("igv1", 83, 85);
    model.geom("part1").run("ige1");
    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").lengthUnit("mm");
    model.geom("part2").geomRep("cadps");
    model.geom("part2").designBooleans(true);
    model.geom("part2").create("imp1", "Import");
    model.geom("part2").feature("imp1").set("filename", "full_ear_hearing_aid_rie.mphbin");
    model.geom("part2").feature("imp1").importData();
    model.geom("part2").create("wp1", "WorkPlane");
    model.geom("part2").feature("wp1").set("unite", true);
    model.geom("part2").feature("wp1").set("planetype", "faceparallel");
    model.geom("part2").feature("wp1").selection("face").set("imp1", 222);
    model.geom("part2").run("wp1");
    model.geom("part2").create("pard1", "PartitionDomains");
    model.geom("part2").feature("pard1").set("partitionwith", "extendedfaces");
    model.geom("part2").feature("pard1").selection("domain").set("imp1", 2);
    model.geom("part2").feature("pard1").selection("extendedface").set("imp1", 34);
    model.geom("part2").run("pard1");
    model.geom("part2").create("del1", "Delete");
    model.geom("part2").feature("del1").selection("input").init(3);
    model.geom("part2").feature("del1").selection("input").set("pard1", 3);
    model.geom("part2").run("del1");
    model.geom("part2").create("pard2", "PartitionDomains");
    model.geom("part2").feature("pard2").selection("domain").set("del1", 9);
    model.geom("part2").feature("pard2").set("partitionwith", "extendedfaces");
    model.geom("part2").feature("pard2").selection("extendedface").set("del1", 184, 195);
    model.geom("part2").run("pard2");
    model.geom("part2").create("fin", "FormUnion");
    model.geom("part2").run("fin");
    model.geom("part2").create("clf1", "CollapseFaces");
    model.geom("part2").feature("clf1").selection("input").set("fin", 63, 65, 109, 126, 129, 181, 188, 236, 247);
    model.geom("part2").run("clf1");
    model.geom("part2").create("ige1", "IgnoreEdges");
    model.geom("part2").feature("ige1").selection("input")
         .set("clf1", 11, 18, 23, 24, 39, 45, 109, 121, 159, 186, 187, 198, 244, 267, 278, 300, 484);
    model.geom("part2").run("ige1");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "full_ear_hearing_aid_full_ear.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("imp1").set("includevirtual", false);
    model.component("comp1").geom("geom1").run("imp1");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(2);
    model.component("comp1").view("view1").hideObjects("hide1").set("imp1", 1, 2, 4, 26, 46);

    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new double[]{111, 75.5, 72});
    model.component("comp1").geom("geom1").feature("pi1").set("specify", "eulerang");
    model.component("comp1").geom("geom1").feature("pi1").set("eulerang", new int[]{0, 80, 58});
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "normalvector");
    model.component("comp1").geom("geom1").feature("wp1").set("normalvector", new double[]{0.4, -2, 0.4});
    model.component("comp1").geom("geom1").feature("wp1").set("normalcoord", new double[]{110, 59, 40.1});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").set("workplanepart", "wp1");
    model.component("comp1").geom("geom1").feature("pi2").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new double[]{-5.25, -6.5, 2.1});
    model.component("comp1").geom("geom1").feature("pi2").set("rot", 100);
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("pi1", 1);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("pi2", 58);
    model.component("comp1").geom("geom1").run("pare1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 90, 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 85, 1);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 60, 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "vertices");
    model.component("comp1").geom("geom1").feature("wp3").selection("vertex1").set("pt1", 1);
    model.component("comp1").geom("geom1").feature("wp3").selection("vertex2").set("pt2", 1);
    model.component("comp1").geom("geom1").feature("wp3").selection("vertex3").set("pare1", 234);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 4.5, 1, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", -0.5, 1, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 7.5, 2, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 2.5, 2, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 9, 3, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 6, 3, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 10.5, 4, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").setIndex("table", 10.5, 4, 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .setIndex("table", 10.955868282658571, 5, 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .setIndex("table", 12.31074458395372, 5, 1);
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("swe1", "Sweep");
    model.component("comp1").geom("geom1").feature("swe1").set("includefinal", false);
    model.component("comp1").geom("geom1").feature("swe1").set("crossfaces", true);
    model.component("comp1").geom("geom1").feature("swe1").set("manualdir", false);
    model.component("comp1").geom("geom1").feature("swe1").selection("enttosweep").set("pare1", 169, 176);
    model.component("comp1").geom("geom1").feature("swe1").selection("edge").set("wp3", 1, 2, 3, 4, 5);
    model.component("comp1").geom("geom1").feature("swe1").set("spineperpend", true);
    model.component("comp1").geom("geom1").feature("swe1").selection("enttomatch").set("pi1", 1);
    model.component("comp1").geom("geom1").feature("swe1").set("adjustlenend", 1);
    model.component("comp1").geom("geom1").feature("swe1").set("spineperpstart", true);
    model.component("comp1").geom("geom1").feature("swe1").set("adjustlen", 0.3);
    model.component("comp1").geom("geom1").feature("swe1").set("manualdir", true);
    model.component("comp1").geom("geom1").feature("swe1").set("reversedir", true);
    model.component("comp1").geom("geom1").run("swe1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pi1", "swe1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").runPre("uni1");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pare1", "pi1", "swe1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("imp1", "uni1");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input")
         .set("uni2", 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 32, 33, 34, 37, 38, 39, 41);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp4").selection("face").set("del1", 3);
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").create("rt1", "RigidTransform");
    model.component("comp1").geom("geom1").feature("rt1").selection("input").set("del1");
    model.component("comp1").geom("geom1").feature("rt1").set("workplaneobj", "wp4");
    model.component("comp1").geom("geom1").run("rt1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input").set("fin", 19, 193);
    model.component("comp1").geom("geom1").run("cmf1");
    model.component("comp1").geom("geom1").create("clf1", "CollapseFaces");
    model.component("comp1").geom("geom1").feature("clf1").selection("input").set("cmf1", 19, 221, 224);
    model.component("comp1").geom("geom1").run("clf1");
    model.component("comp1").geom("geom1").create("cmf2", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf2").selection("input")
         .set("clf1", 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 26, 80, 162, 195, 232, 234, 241, 242, 244, 249, 257, 258, 261, 262, 266, 267, 273, 274, 275, 281, 282, 283);
    model.component("comp1").geom("geom1").run("cmf2");
    model.component("comp1").geom("geom1").feature().duplicate("cmf3", "cmf2");
    model.component("comp1").geom("geom1").feature("cmf3").selection("input").set("cmf2", 189, 190);
    model.component("comp1").geom("geom1").run("cmf3");
    model.component("comp1").geom("geom1").feature().duplicate("cmf4", "cmf3");
    model.component("comp1").geom("geom1").feature("cmf4").selection("input").set("cmf3", 19, 36, 143, 144, 145);
    model.component("comp1").geom("geom1").run("cmf4");
    model.component("comp1").geom("geom1").create("mrv1", "MergeVertices");
    model.component("comp1").geom("geom1").feature("mrv1").selection("keepvtx").set("cmf4", 343);
    model.component("comp1").geom("geom1").feature("mrv1").selection("removevtx").set("cmf4", 344);
    model.component("comp1").geom("geom1").run("mrv1");
    model.component("comp1").geom("geom1").create("mrv2", "MergeVertices");
    model.component("comp1").geom("geom1").feature("mrv2").selection("keepvtx").set("mrv1", 335);
    model.component("comp1").geom("geom1").feature("mrv2").selection("removevtx").set("mrv1", 336);
    model.component("comp1").geom("geom1").run("mrv2");
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input")
         .set("mrv2", 337, 338, 339, 340, 360, 367, 378, 379, 380, 383, 385, 391, 410, 412, 472, 475, 483, 485, 489, 491, 502, 503);
    model.component("comp1").geom("geom1").run("igv1");
    model.component("comp1").geom("geom1").create("cmf5", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf5").selection("input")
         .set("igv1", 11, 12, 13, 14, 15, 16, 17, 18, 28, 35, 36, 54, 55, 64, 106, 118, 139, 140, 158, 166, 173, 179);
    model.component("comp1").geom("geom1").run("cmf5");
    model.component("comp1").geom("geom1").create("cmf6", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf6").selection("input")
         .set("cmf5", 15, 16, 28, 29, 35, 36, 39, 40, 41, 50, 72, 79, 123, 125, 126, 137, 140, 149, 153);
    model.component("comp1").geom("geom1").run("cmf6");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("cmf6", 123, 124, 174, 235, 250, 344, 346, 378);
    model.component("comp1").geom("geom1").run("ige1");

    model.title(null);

    model.description("");

    model.label("full_ear_hearing_aid_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
