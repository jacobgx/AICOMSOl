/*
 * pcb_designer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:58 by COMSOL 6.3.0.290. */
public class pcb_designer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rhoCu", "8900[kg/m^3]", "\u94dc\u5bc6\u5ea6");
    model.param().set("MCu", "68[g/mol]", "\u94dc\u6469\u5c14\u8d28\u91cf");
    model.param().set("Iavg", "2[A/dm^2]", "\u9634\u6781\u7684\u5e73\u5747\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i0", "10[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("alphaa", "1.5", "\u9633\u6781\u4f20\u9012\u7cfb\u6570");
    model.param()
         .set("CopperArea", "0.4262900321[in^2]", "\u6765\u81ea\u5bfc\u5165 PCB \u5e03\u5c40\u7684\u94dc\u5f15\u7ebf\u7684\u9762\u79ef");
    model.param().set("DummyArea", "1.1869299746[in^2]", "\u865a\u62df\u56fe\u6848\u9762\u79ef");
    model.param().set("CathodeArea", "CopperArea+DummyArea", "\u9634\u6781\u9762\u79ef");
    model.param().set("Itot", "Iavg*CathodeArea", "\u603b\u5916\u52a0\u7535\u6d41");
    model.param().set("PlatingThkavg", "10[um]", "\u76ee\u6807\u7535\u9540\u5e73\u5747\u539a\u5ea6");
    model.param().set("PlatingRateavg", "Iavg/2/F_const*MCu/rhoCu", "\u5e73\u5747\u7535\u9540\u901f\u7387");
    model.param().set("PlatingTime", "PlatingThkavg/PlatingRateavg", "\u603b\u7535\u9540\u65f6\u95f4");

    model.component("comp1").geom("geom1").insertFile("pcb_designer_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1")
         .label("\u7535\u89e3\u8d28\u626b\u63a0\u7f51\u683c\u533a\u57df 2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", 0);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2")
         .label("\u7535\u89e3\u8d28\u626b\u63a0\u7f51\u683c\u533a\u57df");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"blk3", "boxsel1"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("PCB \u9876\u90e8\u7535\u4ecb\u8d28");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").label("PCB \u9876\u90e8");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"unisel1", "adjsel1"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("PCB\uff08\u65e0\u9634\u6781\uff09");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"blk1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"blk1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").run("difsel1");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_unisel1_bnd");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("ItotCathode", "Iavg*intop1(1)", "\u9634\u6781\u603b\u7535\u6d41");
    model.component("comp1").variable("var1")
         .set("phil_initial", "-(BathDepth-z)*Itot/(50[S/m])/(BathWidth*BathHeight)", "\u7535\u89e3\u8d28\u7535\u4f4d\u521d\u59cb\u503c");
    model.component("comp1").variable("var1")
         .set("thickness_cathode", "-cd.iloc_er1/2/F_const*MCu/rhoCu*PlatingTime", "\u9634\u6781\u4e0a\u7684\u539a\u5ea6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7535\u89e3\u8d28");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte_conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"50"});

    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es1").selection().named("geom1_unisel1_bnd");
    model.component("comp1").physics("cd").feature("es1").set("BoundaryCondition", "TotalCurrent");
    model.component("comp1").physics("cd").feature("es1").set("Itl", "-ItotCathode");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0", "i0");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("alphaa", "alphaa");
    model.component("comp1").physics("cd").create("es2", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es2").selection().named("geom1_ext1_bnd");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("i0", "i0");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("alphaa", "alphaa");
    model.component("comp1").physics("cd").feature("init1").set("phil", "phil_initial");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_unisel3");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("geom1_unisel1_bnd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_unisel2");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(9);
    model.component("comp1").mesh("mesh1").feature("swe1").set("facemethod", "tri");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection().set(9);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1")
         .set("numelem", "round((PCBThickness/1.5[mm]>=1)*PCBThickness/1.5[mm]+(PCBThickness/1.5[mm]<1),0)");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().named("geom1_boxsel1");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2")
         .set("numelem", "((PCBOffset-PCBThickness)/2[mm]>=1)*(PCBOffset-PCBThickness)/2[mm]+((PCBOffset-PCBThickness)/2[mm]<1)");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("geom1_ext3_dom");
    model.component("comp1").mesh("mesh1").feature("swe2").selection("sourceface").named("geom1_wp5_bnd");
    model.component("comp1").mesh("mesh1").feature("swe2").set("facemethod", "tri");
    model.component("comp1").mesh("mesh1").feature("swe2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").selection().named("geom1_wp5_bnd");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1")
         .set("numelem", "(ApertureThickness/1.5[mm]>=1)*ApertureThickness/1.5[mm]+(ApertureThickness/1.5[mm]<1)");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-6");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u9634\u6781");
    model.result().dataset("surf1").set("param", "expr");
    model.result().dataset("surf1").selection().named("geom1_unisel1_bnd");
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").label("\u9634\u6781\u94dc\u5e03\u5c40");
    model.result().dataset("surf2").set("param", "expr");
    model.result().dataset("surf2").selection().named("geom1_csel1_bnd");
    model.result().dataset().create("surf3", "Surface");
    model.result().dataset("surf3").label("PCB\uff08\u65e0\u9634\u6781\uff09");
    model.result().dataset("surf3").selection().named("geom1_difsel1");
    model.result().dataset().create("surf4", "Surface");
    model.result().dataset("surf4").label("\u58c1");
    model.result().dataset("surf4").selection().set(1, 2, 3, 4, 5, 7, 8, 11, 12);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u9634\u6781\u539a\u5ea6");
    model.result("pg1").set("data", "surf2");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "thickness_cathode");
    model.result("pg1").feature("surf1").set("descr", "\u9634\u6781\u4e0a\u7684\u539a\u5ea6");
    model.result("pg1").feature("surf1").set("unit", "\u00b5m");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "label");
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u9634\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "cd.iloc_er1");
    model.result("pg2").feature("surf1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("surf1").set("expr", "-cd.iloc_er1");
    model.result("pg2").feature("surf1").set("unit", "A/dm^2");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("descr", "\u9634\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u539a\u5ea6\u5206\u5e03\u548c\u7535\u573a\u7ebf");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").feature("surf1").set("expr", "thickness_cathode");
    model.result("pg3").feature("surf1").set("unit", "\u00b5m");
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("data", "surf3");
    model.result("pg3").feature("surf2").set("coloring", "uniform");
    model.result("pg3").feature("surf2").set("color", "custom");
    model.result("pg3").feature("surf2")
         .set("customcolor", new double[]{0.03529411926865578, 0.4627451002597809, 0.03529411926865578});
    model.result("pg3").run();
    model.result("pg3").create("surf3", "Surface");
    model.result("pg3").feature("surf3").set("data", "surf4");
    model.result("pg3").feature("surf3").set("expr", "1");
    model.result("pg3").feature("surf3").set("coloring", "uniform");
    model.result("pg3").feature("surf3").set("color", "white");
    model.result("pg3").run();
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("selnumber", 50);
    model.result("pg3").feature("str1").selection().named("geom1_unisel1_bnd");
    model.result("pg3").feature("str1").set("linetype", "ribbon");
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").run();
    model.result("pg3").set("titletype", "label");
    model.result("pg3").run();

    model.title("\u5370\u5237\u7535\u8def\u677f\u7535\u9540");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u4e8c\u6b21\u7535\u6d41\u5206\u5e03\u201d\u63a5\u53e3\u6a21\u62df\u4e09\u7ef4\u5370\u5237\u7535\u8def\u677f (PCB) \u7684\u7535\u9540\u3002\u4e3a\u4f7f PCB \u4e2d\u7535\u9540\u539a\u5ea6\u5747\u5300\u5206\u5e03\uff0c\u5728\u8bbe\u8ba1\u4e2d\u5f15\u5165\u4e86\u4e00\u4e2a\u865a\u8bbe\u56fe\u6848\uff0c\u5e76\u5728\u7535\u9540\u6d74\u4e2d\u5f00\u4e86\u4e00\u4e2a\u5c0f\u5b54\u3002\n\n\u672c\u4f8b\u9700\u8981\u201cECAD \u5bfc\u5165\u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("pcb_designer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
