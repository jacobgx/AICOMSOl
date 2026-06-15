/*
 * aec_shunt_currents_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:04 by COMSOL 6.3.0.290. */
public class aec_shunt_currents_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Electrolyzers");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("R_cell", "0.8[m]", "\u7535\u89e3\u69fd\u534a\u5f84\uff08\u542b\u57ab\u7247\uff09");
    model.param().set("S_gasket", "50[mm]", "\u57ab\u7247\u5bbd\u5ea6");
    model.param().set("H_manifold", "100[mm]", "\u6b67\u7ba1\u9ad8\u5ea6");
    model.param().set("H_holes", "100[mm]", "\u901a\u9053\u5b54\u9ad8\u5ea6");
    model.param().set("H_active", "2*(R_cell-S_gasket-H_holes-H_manifold)", "\u6d3b\u6027\u533a\u57df\u9ad8\u5ea6");
    model.param().set("D_endplate", "30[mm]", "\u7aef\u677f\u539a\u5ea6");
    model.param().set("D_h2", "4[mm]", "\u6c22\u7535\u89e3\u8d28\u5ba4\u539a\u5ea6");
    model.param().set("D_sep", "3[mm]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("D_o2", "4[mm]", "\u6c27\u7535\u89e3\u8d28\u5ba4\u539a\u5ea6");
    model.param().set("D_bpp", "5[mm]", "\u53cc\u6781\u677f\u539a\u5ea6");
    model.param().set("D_cell", "D_h2+D_sep+D_o2+D_bpp", "\u5355\u5143\u539a\u5ea6");
    model.param().set("N_cells", "20", "\u5355\u5143\u6570");

    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("\u6a2a\u622a\u9762\u5256\u9762");
    model.geom("part1").create("c1", "Circle");
    model.geom("part1").feature("c1").set("r", "R_cell-S_gasket");
    model.geom("part1").run("c1");
    model.geom("part1").create("r1", "Rectangle");
    model.geom("part1").feature("r1").set("size", new String[]{"2*R_cell", "H_active"});
    model.geom("part1").feature("r1").set("base", "center");
    model.geom("part1").run("r1");
    model.geom("part1").create("int1", "Intersection");
    model.geom("part1").feature("int1").selection("input").set("c1", "r1");
    model.geom("part1").feature("int1").set("keep", true);
    model.geom("part1").run("int1");
    model.geom("part1").create("del1", "Delete");
    model.geom("part1").feature("del1").selection("input").init();
    model.geom("part1").feature("del1").selection("input").set("r1");
    model.geom("part1").run("del1");
    model.geom("part1").create("r2", "Rectangle");
    model.geom("part1").feature("r2").set("size", new String[]{"R_cell", "H_manifold"});
    model.geom("part1").feature("r2").set("pos", new String[]{"S_gasket/2", "H_active/2"});
    model.geom("part1").run("r2");
    model.geom("part1").create("int2", "Intersection");
    model.geom("part1").feature("int2").selection("input").set("c1", "r2");
    model.geom("part1").feature("int2").set("keep", true);
    model.geom("part1").run("int2");
    model.geom("part1").create("del2", "Delete");
    model.geom("part1").feature("del2").selection("input").init();
    model.geom("part1").feature("del2").selection("input").set("r2");
    model.geom("part1").run("del2");
    model.geom("part1").create("r3", "Rectangle");
    model.geom("part1").feature("r3").set("size", new String[]{"R_cell", "H_holes"});
    model.geom("part1").feature("r3").set("pos", new String[]{"S_gasket/2", "H_active/2+H_manifold"});
    model.geom("part1").run("r3");
    model.geom("part1").create("int3", "Intersection");
    model.geom("part1").feature("int3").selection("input").set("c1", "r3");
    model.geom("part1").run("int3");
    model.geom("part1").create("mir1", "Mirror");
    model.geom("part1").feature("mir1").selection("input").set("int2", "int3");
    model.geom("part1").feature("mir1").set("keep", true);
    model.geom("part1").run("mir1");
    model.geom("part1").create("mir2", "Mirror");
    model.geom("part1").feature("mir2").selection("input").set("int2", "int3", "mir1");
    model.geom("part1").feature("mir2").set("keep", true);
    model.geom("part1").feature("mir2").set("axis", new int[]{0, 1});
    model.geom("part1").run("mir2");
    model.geom().create("part2", "Part", 2);
    model.geom("part2").label("\u6d3b\u6027\u7535\u6c60\u533a\u57df");
    model.geom("part2").create("pi1", "PartInstance");
    model.geom("part2").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part2").feature("pi1").set("part", "part1");
    model.geom("part2").run("pi1");
    model.geom("part2").create("del1", "Delete");
    model.geom("part2").feature("del1").selection("input").init();
    model.geom("part2").feature("del1").selection("input")
         .set("pi1(2)", "pi1(3)", "pi1(4)", "pi1(5)", "pi1(6)", "pi1(7)", "pi1(8)", "pi1(9)");
    model.geom("part2").run("del1");
    model.geom().create("part3", "Part", 2);
    model.geom("part3").label("\u6c22\u6c14\u5165\u53e3\u901a\u9053");
    model.geom("part3").create("pi1", "PartInstance");
    model.geom("part3").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part3").feature("pi1").set("part", "part1");
    model.geom("part3").run("pi1");
    model.geom("part3").create("del1", "Delete");
    model.geom("part3").feature("del1").selection("input").init();
    model.geom("part3").feature("del1").selection("input")
         .set("pi1(1)", "pi1(2)", "pi1(3)", "pi1(4)", "pi1(5)", "pi1(6)", "pi1(8)", "pi1(9)");
    model.geom("part3").run("del1");
    model.geom().create("part4", "Part", 2);
    model.geom("part4").label("\u6c22\u6c14\u51fa\u53e3\u901a\u9053");
    model.geom("part4").create("pi1", "PartInstance");
    model.geom("part4").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part4").feature("pi1").set("part", "part1");
    model.geom("part4").run("pi1");
    model.geom("part4").create("del1", "Delete");
    model.geom("part4").feature("del1").selection("input").init();
    model.geom("part4").feature("del1").selection("input")
         .set("pi1(1)", "pi1(2)", "pi1(3)", "pi1(4)", "pi1(6)", "pi1(7)", "pi1(8)", "pi1(9)");
    model.geom("part4").run("del1");
    model.geom().create("part5", "Part", 2);
    model.geom("part5").label("\u6c22\u6c14\u6b67\u7ba1");
    model.geom("part5").create("pi1", "PartInstance");
    model.geom("part5").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part5").feature("pi1").set("part", "part1");
    model.geom("part5").run("pi1");
    model.geom("part5").create("del1", "Delete");
    model.geom("part5").feature("del1").selection("input").init();
    model.geom("part5").feature("del1").selection("input")
         .set("pi1(1)", "pi1(2)", "pi1(3)", "pi1(5)", "pi1(7)", "pi1(8)", "pi1(9)");
    model.geom("part5").run("del1");
    model.geom().create("part6", "Part", 2);
    model.geom("part6").label("\u6c27\u6c14\u5165\u53e3\u901a\u9053");
    model.geom("part6").create("pi1", "PartInstance");
    model.geom("part6").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part6").feature("pi1").set("part", "part1");
    model.geom("part6").run("pi1");
    model.geom("part6").create("del1", "Delete");
    model.geom("part6").feature("del1").selection("input").init();
    model.geom("part6").feature("del1").selection("input")
         .set("pi1(1)", "pi1(2)", "pi1(3)", "pi1(4)", "pi1(5)", "pi1(6)", "pi1(7)", "pi1(8)");
    model.geom("part6").run("del1");
    model.geom().create("part7", "Part", 2);
    model.geom("part7").label("\u6c27\u6c14\u51fa\u53e3\u901a\u9053");
    model.geom("part7").create("pi1", "PartInstance");
    model.geom("part7").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part7").feature("pi1").set("part", "part1");
    model.geom("part7").run("pi1");
    model.geom("part7").create("del1", "Delete");
    model.geom("part7").feature("del1").selection("input").init();
    model.geom("part7").feature("del1").selection("input")
         .set("pi1(1)", "pi1(2)", "pi1(4)", "pi1(5)", "pi1(6)", "pi1(7)", "pi1(8)", "pi1(9)");
    model.geom("part7").run("del1");
    model.geom().create("part8", "Part", 2);
    model.geom("part8").label("\u6c27\u6c14\u6b67\u7ba1");
    model.geom("part8").create("pi1", "PartInstance");
    model.geom("part8").feature("pi1").set("selkeepnoncontr", false);
    model.geom("part8").feature("pi1").set("part", "part1");
    model.geom("part8").run("pi1");
    model.geom("part8").create("del1", "Delete");
    model.geom("part8").feature("del1").selection("input").init();
    model.geom("part8").feature("del1").selection("input")
         .set("pi1(1)", "pi1(3)", "pi1(4)", "pi1(5)", "pi1(6)", "pi1(7)", "pi1(9)");
    model.geom("part8").run("del1");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1").set("part", "part2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pi1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "D_endplate", 0);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7535\u6d41\u5bfc\u4f53");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", "D_endplate");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pi1").set("part", "part2");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pi1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pi2").set("part", "part5");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pi2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pi3");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pi4").set("part", "part4");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pi4");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "D_h2", 0);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u6c22\u6c14\u7535\u89e3\u8d28\u5ba4");
    model.component("comp1").geom("geom1").feature("ext2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp3").set("quickx", "D_endplate+D_h2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pi1").set("part", "part2");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "D_sep", 0);
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u9694\u819c");
    model.component("comp1").geom("geom1").feature("ext3").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp4").set("quickx", "D_endplate+D_h2+D_sep");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pi1").set("part", "part2");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pi1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pi2").set("part", "part6");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pi2");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pi3").set("part", "part7");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pi3");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("pi4").set("part", "part8");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("pi4");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "D_o2", 0);
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u6c27\u6c14\u7535\u89e3\u8d28\u5ba4");
    model.component("comp1").geom("geom1").feature("ext4").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").label("If N_cells>1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "N_cells>1");
    model.component("comp1").geom("geom1").run("if1");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp5").set("quickx", "D_endplate+D_cell-D_bpp");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("pi1").set("part", "part2");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("pi1");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "D_bpp", 0);
    model.component("comp1").geom("geom1").feature("ext5").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("ext2", "ext3", "ext4");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"N_cells", "1", "1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"D_cell", "0", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("if2", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature("if2").label("If N_cells>2");
    model.component("comp1").geom("geom1").feature("if2").set("condition", "N_cells>2");
    model.component("comp1").geom("geom1").run("if2");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("ext5");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new String[]{"N_cells-1", "1", "1"});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"D_cell", "0", "0"});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").feature("endif2").label("End If N_cells>2");
    model.component("comp1").geom("geom1").run("endif2");
    model.component("comp1").geom("geom1").feature("endif1").label("End If N_cells>1");
    model.component("comp1").geom("geom1").run("endif1");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp6").set("quickx", "D_endplate+N_cells*D_cell-D_bpp");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pi1").set("part", "part2");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("pi1");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").feature().create("ext6", "Extrude");
    model.component("comp1").geom("geom1").feature("ext6").setIndex("distance", "D_endplate", 0);
    model.component("comp1").geom("geom1").feature("ext6").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext6");
    model.component("comp1").geom("geom1").create("wp7", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp7").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp7").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("pi1").set("part", "part3");
    model.component("comp1").geom("geom1").run("wp7");
    model.component("comp1").geom("geom1").feature().create("ext7", "Extrude");
    model.component("comp1").geom("geom1").feature("ext7").label("\u6c22\u6c14\u5165\u53e3\u901a\u9053");
    model.component("comp1").geom("geom1").feature("ext7")
         .setIndex("distance", "D_endplate+D_h2+(N_cells-1)*D_cell", 0);
    model.component("comp1").geom("geom1").feature("ext7").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext7").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("ext7");
    model.component("comp1").geom("geom1").create("wp8", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp8").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp8").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp8").set("quickx", "D_endplate");
    model.component("comp1").geom("geom1").feature("wp8").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp8").geom().feature("pi1").set("part", "part4");
    model.component("comp1").geom("geom1").run("wp8");
    model.component("comp1").geom("geom1").feature().create("ext8", "Extrude");
    model.component("comp1").geom("geom1").feature("ext8").label("\u6c22\u6c14\u51fa\u53e3\u901a\u9053");
    model.component("comp1").geom("geom1").feature("ext8")
         .setIndex("distance", "N_cells*D_cell-D_bpp+D_endplate", 0);
    model.component("comp1").geom("geom1").feature("ext8").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext8").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("ext8");
    model.component("comp1").geom("geom1").create("wp9", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp9").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp9").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp9").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp9").geom().feature("pi1").set("part", "part6");
    model.component("comp1").geom("geom1").run("wp9");
    model.component("comp1").geom("geom1").feature().create("ext9", "Extrude");
    model.component("comp1").geom("geom1").feature("ext9").label("\u6c27\u6c14\u5165\u53e3\u901a\u9053");
    model.component("comp1").geom("geom1").feature("ext9")
         .setIndex("distance", "D_endplate+D_h2+D_sep+D_o2+(N_cells-1)*D_cell", 0);
    model.component("comp1").geom("geom1").feature("ext9").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext9").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("ext9");
    model.component("comp1").geom("geom1").create("wp10", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp10").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp10").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp10").set("quickx", "D_endplate+D_cell-D_bpp-D_o2");
    model.component("comp1").geom("geom1").feature("wp10").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp10").geom().feature("pi1").set("part", "part7");
    model.component("comp1").geom("geom1").run("wp10");
    model.component("comp1").geom("geom1").feature().create("ext10", "Extrude");
    model.component("comp1").geom("geom1").feature("ext10").label("\u6c27\u6c14\u51fa\u53e3\u901a\u9053");
    model.component("comp1").geom("geom1").feature("ext10")
         .setIndex("distance", "(N_cells-1)*D_cell+D_endplate+D_o2", 0);
    model.component("comp1").geom("geom1").feature("ext10").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext10").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("ext10");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u4e0a\u90e8\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", "H_active/2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "H_active/2+H_manifold+H_holes/2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u4e0b\u90e8\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "-H_active/2-H_manifold-H_holes/2");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "H_active/2");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"boxsel1", "boxsel2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u901a\u9053");
    model.component("comp1").geom("geom1").feature("unisel2")
         .set("input", new String[]{"ext7", "ext8", "ext9", "ext10"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u6d3b\u6027\u7535\u6c60\u4f53\u79ef");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"unisel1", "unisel2"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u4e0a\u90e8\u6b67\u7ba1\u8fb9\u754c");
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u4e0b\u90e8\u6b67\u7ba1\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"boxsel2"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3")
         .label("\u6d3b\u6027\u7535\u6c60\u4f53\u79ef\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"comsel1"});
    model.component("comp1").geom("geom1").feature("adjsel3").set("interior", true);
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").label("\u7535\u89e3\u8d28\u5ba4");
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"csel2", "csel3", "csel4"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").label("H2 \u7535\u6781");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel1")
         .set("input", new String[]{"csel1", "csel2", "adjsel3"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("intsel2", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel2").label("O2 \u7535\u6781");
    model.component("comp1").geom("geom1").feature("intsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel2")
         .set("input", new String[]{"csel1", "csel4", "adjsel3"});
    model.component("comp1").geom("geom1").run("intsel2");
    model.component("comp1").geom("geom1").create("intsel3", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel3")
         .label("\u4e0a\u90e8\u6b67\u7ba1 - \u6d3b\u6027\u7535\u6c60\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("intsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel3").set("input", new String[]{"adjsel1", "adjsel3"});
    model.component("comp1").geom("geom1").run("intsel3");
    model.component("comp1").geom("geom1").create("intsel4", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel4")
         .label("\u4e0b\u90e8\u6b67\u7ba1 - \u6d3b\u6027\u7535\u6c60\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("intsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel4").set("input", new String[]{"adjsel2", "adjsel3"});
    model.component("comp1").geom("geom1").run("intsel4");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4")
         .label("\u6b67\u7ba1 - \u6d3b\u6027\u7535\u6c60\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("unisel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"intsel3", "intsel4"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3")
         .label("\u4e0a\u90e8\u6d3b\u6027\u533a\u57df\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", "H_active/2-H_manifold/2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", "H_active/2+H_manifold/2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4")
         .label("\u4e0b\u90e8\u6d3b\u6027\u533a\u57df\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmin", "-(H_active/2+H_manifold/2)");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmax", "-(H_active/2-H_manifold/2)");
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5")
         .label("\u4e0a/\u4e0b\u6b67\u7ba1\u548c\u6d3b\u6027\u533a\u57df\u8fb9\u754c\uff08\u7528\u4e8e\u7f51\u683c\u5212\u5206\uff09");
    model.component("comp1").geom("geom1").feature("unisel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel5")
         .set("input", new String[]{"adjsel1", "adjsel2", "boxsel3", "boxsel4"});
    model.component("comp1").geom("geom1").run("unisel5");
    model.component("comp1").geom("geom1").create("unisel6", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel6")
         .label("\u4e0a/\u4e0b\u6d3b\u6027\u533a\u57df\u8fb9\u754c\uff08\u7528\u4e8e\u7f51\u683c\u5212\u5206\uff09");
    model.component("comp1").geom("geom1").feature("unisel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel6").set("input", new String[]{"boxsel3", "boxsel4"});
    model.component("comp1").geom("geom1").run("unisel6");
    model.component("comp1").geom("geom1").create("unisel7", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel7").label("\u901a\u9053\u548c\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("unisel7").set("input", new String[]{"unisel1", "unisel2"});

    model.title(null);

    model.description("");

    model.label("aec_shunt_currents_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
