/*
 * zero_gap_aec_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:07 by COMSOL 6.3.0.290. */
public class zero_gap_aec_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Electrolyzers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("H_gde", "2[mm]", "\u6c14\u4f53\u6269\u6563\u7535\u6781\u9ad8\u5ea6");
    model.param().set("H_sep", "0.5[mm]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("H_ch", "5[mm]", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("W_ch", "12[mm]", "\u901a\u9053\u6700\u5927\u5bbd\u5ea6");
    model.param().set("W_rib", "8[mm]", "\u808b\u5bbd\u5ea6\uff08\u53ca\u901a\u9053\u6700\u5c0f\u5bbd\u5ea6\uff09");
    model.param().set("W_ribch", "W_rib+W_ch", "\u901a\u9053\u95f4\u8ddd");
    model.param().set("H_cell", "2*H_gde+H_ch+H_sep", "\u5355\u5143\u9ad8\u5ea6");
    model.param().set("H_bpp", "0.25[mm]", "\u53cc\u6781\u677f\u539a\u5ea6");
    model.param().set("L_cell", "10[cm]", "\u5355\u5143\u957f\u5ea6");
    model.param().set("W_dev", "(W_ch/2-W_rib/2)/((H_ch-H_bpp)/H_bpp-1)", "\u51e0\u4f55\u52a9\u53c2\u6570");
    model.param().set("L_inl", "W_ch/2", "\u5165\u53e3\u533a\u57df\u957f\u5ea6");
    model.param().set("L_outl", "W_ch/2", "\u51fa\u53e3\u533a\u57df\u957f\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "W_rib/2", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "W_ribch/2-W_rib/2+W_dev", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "H_ch-H_bpp", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "W_ribch/2", 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "H_ch-H_bpp", 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "W_ribch/2", 4, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "H_ch", 4, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "W_ribch/2-W_rib/2", 5, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "H_ch", 5, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "W_rib/2-W_dev", 6, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "H_bpp", 6, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 7, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "H_bpp", 7, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("disply", "H_sep/2+H_gde");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mov1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"W_ribch/2", "H_cell+H_sep/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", "H_sep/2", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", "H_gde", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", "H_ch", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", "H_gde", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input")
         .set("mov1", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1")
         .set("pos", new String[]{"0", "H_cell"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord1", new String[]{"W_rib/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex2").set("mov1", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").selection("vertex2").set("mov1", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .set("coord1", new String[]{"W_ribch/2-W_rib/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .setIndex("coord1", "H_sep/2+H_gde+H_ch+H_gde+H_sep", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("mir2", "mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir2").selection("input").init();
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir2").selection("input")
         .set("ls1", "ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L_cell", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("ext1", 48, 10, 43, 22);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "L_inl", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").set("ext2", 105, 113, 101, 111);
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "L_outl", 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u9694\u819c");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 5, 10, 15, 16, 21, 23);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u6c27\u6c14\u901a\u9053");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 2, 3, 12, 18, 26, 27);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u6c27\u6c14\u6269\u6563\u7535\u6781");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 6, 11, 17, 24);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u6c22\u6c14\u901a\u9053");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 9, 14, 19, 22);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").clear("fin");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 1, 4, 8, 20, 25, 28);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u6c22\u6c14\u6269\u6563\u7535\u6781");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("fin", 9, 14, 19, 22);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u53cc\u6781\u677f");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("fin", 7, 13);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u6c22\u6c14\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("fin", 1, 14);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("\u6c27\u6c14\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("fin", 5, 11);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").label("\u6c22\u6c14\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("fin", 134, 137);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").create("sel10", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel10").label("\u6c27\u6c14\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").set("fin", 135, 136);
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u901a\u9053");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel2", "sel4"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u6c14\u4f53\u6269\u6563\u7535\u6781");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"sel3", "sel5"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3")
         .label("\u901a\u9053\u548c\u6c14\u4f53\u6269\u6563\u7535\u6781");
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"unisel1", "unisel2"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("unisel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"sel7", "sel8"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("unisel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel5").set("input", new String[]{"sel9", "sel10"});
    model.component("comp1").geom("geom1").run("unisel5");
    model.component("comp1").geom("geom1").create("unisel6", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel6").label("\u7535\u89e3\u8d28\u57df");
    model.component("comp1").geom("geom1").feature("unisel6")
         .set("input", new String[]{"sel1", "unisel1", "unisel2"});
    model.component("comp1").geom("geom1").run("unisel6");
    model.component("comp1").geom("geom1").create("sel11", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel11").label("\u4e09\u89d2\u5f62\u7f51\u683c\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").set("fin", 30, 42, 63, 68);
    model.component("comp1").geom("geom1").run("sel11");
    model.component("comp1").geom("geom1").create("sel12", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel12").label("\u6620\u5c04\u7f51\u683c\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel12").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel12").selection("selection")
         .set("fin", 21, 24, 33, 36, 39, 48, 51, 57, 60, 66, 71, 75, 78, 81);
    model.component("comp1").geom("geom1").run("sel12");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").set("fin", 58, 61, 67, 72, 76, 79, 82);
    model.component("comp1").geom("geom1").run("mcf1");

    model.title(null);

    model.description("");

    model.label("zero_gap_aec_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
