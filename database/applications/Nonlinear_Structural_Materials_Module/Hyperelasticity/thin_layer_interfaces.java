/*
 * thin_layer_interfaces.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:32 by COMSOL 6.3.0.290. */
public class thin_layer_interfaces {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.param().label("\u53c2\u6570\uff1a\u51e0\u4f55\u5f62\u72b6");
    model.param().set("L", "1[m]");
    model.param().descr("L", "\u957f\u5ea6");
    model.param().set("R", "1[m]/sqrt(2)");
    model.param().descr("R", "\u534a\u5f84");
    model.param().set("d", "1[m]");
    model.param().descr("d", "\u9762\u5916\u5c3a\u5bf8");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570\uff1a\u672c\u4f53\u6750\u6599");
    model.param("par2").set("RhoBulk", "1[kg/m^3]");
    model.param("par2").descr("RhoBulk", "\u5bc6\u5ea6");
    model.param("par2").set("KBulk", "50[N/mm^2]");
    model.param("par2").descr("KBulk", "\u4f53\u79ef\u6a21\u91cf");
    model.param("par2").set("MuBulk", "10[N/mm^2]");
    model.param("par2").descr("MuBulk", "Lame \u53c2\u6570");
    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570\uff1a\u8584\u5c42");
    model.param("par3").set("th", "L/10");
    model.param("par3").descr("th", "\u8584\u5c42\u539a\u5ea6");
    model.param("par3").set("RhoBnd", "1[kg/m^3]");
    model.param("par3").descr("RhoBnd", "\u5bc6\u5ea6");
    model.param("par3").set("KRatio", "0.2");
    model.param("par3").descr("KRatio", "\u4f53\u79ef\u6a21\u91cf\u7684\u6bd4\u4f8b\u56e0\u5b50");
    model.param("par3").set("KBnd", "KRatio*KBulk");
    model.param("par3").descr("KBnd", "\u4f53\u79ef\u6a21\u91cf");
    model.param("par3").set("MuRatio", "100");
    model.param("par3").descr("MuRatio", "Lame \u53c2\u6570\u7684\u6bd4\u4f8b\u56e0\u5b50");
    model.param("par3").set("MuBnd", "MuRatio*MuBulk");
    model.param("par3").descr("MuBnd", "Lame \u53c2\u6570");
    model.param("par3").set("AlphaRatio", "1");
    model.param("par3").descr("AlphaRatio", "\u521a\u5ea6\u7684\u6bd4\u4f8b\u56e0\u5b50");
    model.param("par3").set("AlphaBnd", "AlphaRatio*(KBulk/th)");
    model.param("par3").descr("AlphaBnd", "\u5f39\u7c27\u521a\u5ea6");
    model.param().create("par4");
    model.param("par4").label("\u53c2\u6570\uff1a\u8fb9\u754c\u6761\u4ef6");
    model.param("par4").set("stretch", "0");
    model.param("par4").descr("stretch", "\u4f38\u957f\u7387");
    model.param().create("par5");
    model.param("par5").label("\u53c2\u6570\uff1a\u7f51\u683c");
    model.param("par5").set("nel", "20");
    model.param("par5").descr("nel", "\u5355\u5143\u6570");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "L");
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("r", "R");
    model.component("comp1").geom("geom1").feature("ca1").set("angle2", 45);
    model.component("comp1").geom("geom1").feature().duplicate("ca2", "ca1");
    model.component("comp1").geom("geom1").feature("ca2").set("center", new String[]{"L", "L"});
    model.component("comp1").geom("geom1").feature("ca2").set("angle1", 180);
    model.component("comp1").geom("geom1").feature("ca2").set("angle2", 225);
    model.component("comp1").geom("geom1").run("ca2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("sq1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("ca1", "ca2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().set(1, 2);

    model.component("comp1").physics("solid").prop("d").set("d", "d");
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm1").selection().set(1, 2);
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "SaintVenantKirchhoff");
    model.component("comp1").physics("solid").feature("hmm1").set("IsotropicOption", "KG");
    model.component("comp1").physics("solid").feature("hmm1")
         .set("Compressibility_SaintVenantKirchhoff", "NearlyIncompressible");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u672c\u4f53");
    model.component("comp1").material("mat1").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat1").propertyGroup("KG").set("K", new String[]{"KBulk"});
    model.component("comp1").material("mat1").propertyGroup("KG").set("G", new String[]{"MuBulk"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"RhoBulk"});

    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2, 3, 4, 5);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(1, 6);
    model.component("comp1").physics("solid").feature("disp1").set("coordinateSystem", "sys1");
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "L*stretch/2", 1);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3, 5, 7, 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", "nel");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", "nel");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis3", "dis2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(1, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", "nel*2");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").label("\u7406\u60f3\u754c\u9762");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "AlphaBnd", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "N/m^3", 0);
    model.study("std1").feature("stat").setIndex("pname", "AlphaBnd", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "N/m^3", 0);
    model.study("std1").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0.1,0.1,0.5)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u7406\u60f3\u754c\u9762");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u7406\u60f3\u754c\u9762");
    model.result("pg1").set("paramindicator", "");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "solid.PxX");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();

    model.component("comp1").physics("solid").create("tl1", "ThinLayer", 1);
    model.component("comp1").physics("solid").feature("tl1").selection().set(7, 8);
    model.component("comp1").physics("solid").feature("tl1").set("lth", "th");
    model.component("comp1").physics("solid").feature("tl1").label("\u56fa\u4f53\u8fd1\u4f3c");
    model.component("comp1").physics("solid").feature("tl1").create("hmm1", "HyperelasticModel", 1);
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").selection().set(7, 8);
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1")
         .set("MaterialModel", "SaintVenantKirchhoff");
    model.component("comp1").physics("solid").feature("tl1").feature("hmm1").set("IsotropicOption", "KG");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u8584\u5c42");
    model.component("comp1").material("mat2").selection().geom("geom1", 1);
    model.component("comp1").material("mat2").selection().set(7, 8);
    model.component("comp1").material("mat2").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat2").propertyGroup("KG").set("K", new String[]{"KBnd"});
    model.component("comp1").material("mat2").propertyGroup("KG").set("G", new String[]{"MuBnd"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"RhoBnd"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "AlphaBnd", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "N/m^3", 0);
    model.study("std2").feature("stat").setIndex("pname", "AlphaBnd", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "N/m^3", 0);
    model.study("std2").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0.1,0.1,0.5)", 0);
    model.study("std2").feature("stat").setIndex("pname", "AlphaBnd", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "N/m^3", 1);
    model.study("std2").feature("stat").setIndex("pname", "AlphaBnd", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "N/m^3", 1);
    model.study("std2").feature("stat").setIndex("pname", "MuRatio", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "range(1,-0.25,0.25) 0.01", 1);
    model.study("std2").feature("stat").set("sweeptype", "filled");
    model.study("std2").feature("stat").set("pcontinuationmode", "manual");
    model.study("std2").feature("stat").set("pcontinuation", "stretch");
    model.study("std2").setGenPlots(false);
    model.study("std2").label("\u56fa\u4f53\u8fd1\u4f3c");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u56fa\u4f53\u8fd1\u4f3c");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u4f7f\u7528\u56fa\u4f53\u539a\u5ea6\u8fd1\u4f3c\u6cd5\u7684\u8584\u5c42");
    model.result("pg2").set("paramindicator", "");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "solid.PxX");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 4, 1);
    model.result("pg2").run();
    model.result("pg2").create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("arwl1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("arwl1").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("arwl1").create("sel1", "Selection");
    model.result("pg2").feature("arwl1").feature("sel1").selection().set(1, 6);
    model.result("pg2").run();
    model.result("pg2").set("view", "view2");
    model.result("pg2").run();

    model.component("comp1").physics("solid").create("tl2", "ThinLayer", 1);
    model.component("comp1").physics("solid").feature("tl2").label("\u819c\u8fd1\u4f3c");
    model.component("comp1").physics("solid").feature("tl2").selection().set(7, 8);
    model.component("comp1").physics("solid").feature("tl2").set("lth", "th");
    model.component("comp1").physics("solid").feature("tl2").set("thicknessApproximation", "membrane");
    model.component("comp1").physics("solid").feature("tl2").create("hmm1", "HyperelasticModel", 1);
    model.component("comp1").physics("solid").feature("tl2").feature("hmm1").selection().set(7, 8);
    model.component("comp1").physics("solid").feature("tl2").feature("hmm1")
         .set("MaterialModel", "SaintVenantKirchhoff");
    model.component("comp1").physics("solid").feature("tl2").feature("hmm1").set("IsotropicOption", "KG");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u819c\u8fd1\u4f3c");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/tl1"});
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "AlphaBnd", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "N/m^3", 0);
    model.study("std3").feature("stat").setIndex("pname", "AlphaBnd", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "N/m^3", 0);
    model.study("std3").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(0.1,0.1,0.5)", 0);
    model.study("std3").feature("stat").setIndex("pname", "AlphaBnd", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "N/m^3", 1);
    model.study("std3").feature("stat").setIndex("pname", "AlphaBnd", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "", 1);
    model.study("std3").feature("stat").setIndex("punit", "N/m^3", 1);
    model.study("std3").feature("stat").setIndex("pname", "MuRatio", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "10^{range(-3,1,4)}", 1);
    model.study("std3").feature("stat").set("sweeptype", "filled");
    model.study("std3").feature("stat").set("pcontinuationmode", "manual");
    model.study("std3").feature("stat").set("pcontinuation", "stretch");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u819c\u8fd1\u4f3c");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u4ece\u819c\u8fd1\u4f3c\u5230\u7406\u60f3\u754c\u9762\u7684\u8fc7\u6e21");
    model.result("pg3").set("paramindicator", "");
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").set("arrayshape", "square");
    model.result("pg3").set("paddingsquare", "absolute");
    model.result("pg3").set("columnpadding", "L");
    model.result("pg3").set("rowpadding", "-2.4*L");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("arraydim", "2");
    model.result("pg3").feature("surf1").set("data", "dset3");
    model.result("pg3").feature("surf1").set("expr", "solid.PxX");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature("surf2").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").setIndex("looplevel", 7, 1);
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature().duplicate("surf3", "surf2");
    model.result("pg3").feature("surf3").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").setIndex("looplevel", 6, 1);
    model.result("pg3").feature().duplicate("surf4", "surf3");
    model.result("pg3").feature("surf4").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf4").setIndex("looplevel", 4, 1);
    model.result("pg3").feature().duplicate("surf5", "surf4");
    model.result("pg3").feature("surf5").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf5").setIndex("looplevel", 3, 1);
    model.result("pg3").feature().duplicate("surf6", "surf5");
    model.result("pg3").feature("surf6").set("arraydim", "2");
    model.result("pg3").run();
    model.result("pg3").feature("surf6").set("data", "dset1");
    model.result("pg3").run();
    model.result("pg3").create("tlan1", "TableAnnotation");
    model.result("pg3").feature("tlan1").set("arraydim", "2");
    model.result("pg3").feature("tlan1").set("source", "localtable");
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 0, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "3/2*L", 0, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "$\\mu_\\mathrm{ratio}$ = 10000", 0, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "2*L", 1, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "3/2*L", 1, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "$\\mu_\\mathrm{ratio}$ = 1000", 1, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "4*L", 2, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "3/2*L", 2, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "$\\mu_\\mathrm{ratio}$ = 100", 2, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "-3/2*L", 3, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "$\\mu_\\mathrm{ratio}$ = 1", 3, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "2*L", 4, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "-3/2*L", 4, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "$\\mu_\\mathrm{ratio}$ = 0.1", 4, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "4*L", 5, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "-3/2*L", 5, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "\\unicode{\u7406\u60f3\u754c\u9762}", 5, 2);
    model.result("pg3").feature("tlan1").set("showpoint", false);
    model.result("pg3").feature("tlan1").set("latexmarkup", true);
    model.result("pg3").run();
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7ebf\u7ed3\u679c\u56fe\uff1a\u819c\u8fd1\u4f3c");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("xlog", true);
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\\mu<sub>ratio</sub>");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4")
         .set("ylabel", "\u7b2c\u4e00\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b (MPa)");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("legendpos", "lowerleft");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(4);
    model.result("pg4").feature("ptgr1").set("expr", "mean(side(1,solid.PxX))");
    model.result("pg4").feature("ptgr1").set("xdatasolnumtype", "level2");
    model.result("pg4").feature("ptgr1").set("data", "dset3");
    model.result("pg4").feature("ptgr1").setIndex("looplevelinput", "last", 0);
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "Membrane approximation", 0);
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u819c\u8fd1\u4f3c", 0);
    model.result("pg4").feature("ptgr1").set("linemarker", "cycle");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("lnsg1", "LineSegments");
    model.result("pg4").feature("lnsg1").set("markerpos", "datapoints");
    model.result("pg4").feature("lnsg1").set("linewidth", "preference");
    model.result("pg4").feature("lnsg1").set("data", "dset1");
    model.result("pg4").feature("lnsg1").setIndex("looplevelinput", "last", 0);
    model.result("pg4").feature("lnsg1").setIndex("xexpr", 0, 0);
    model.result("pg4").feature("lnsg1").setIndex("xexpr", "1e-4", 1);
    model.result("pg4").feature("lnsg1").setIndex("xexpr", "1e4", 2);
    model.result("pg4").feature("lnsg1").setIndex("yexpr", "aveop1(solid.PxX)", 0);
    model.result("pg4").feature("lnsg1").setIndex("yexpr", "aveop1(solid.PxX)", 1);
    model.result("pg4").feature("lnsg1").setIndex("yexpr", "aveop1(solid.PxX)", 2);
    model.result("pg4").feature("lnsg1").set("legend", true);
    model.result("pg4").feature("lnsg1").set("legendmethod", "manual");
    model.result("pg4").feature("lnsg1").setIndex("legends", "\u7406\u60f3\u754c\u9762", 0);

    model.component("comp1").physics("solid").create("tl3", "ThinLayer", 1);
    model.component("comp1").physics("solid").feature("tl3").selection().set(7, 8);
    model.component("comp1").physics("solid").feature("tl3").set("lth", "th");
    model.component("comp1").physics("solid").feature("tl3").set("thicknessApproximation", "spring");
    model.component("comp1").physics("solid").feature("tl3").label("\u5f39\u7c27\u8fd1\u4f3c");
    model.component("comp1").physics("solid").feature("tl3").create("spm1", "SpringMaterial", 1);
    model.component("comp1").physics("solid").feature("tl3").feature("spm1")
         .set("kPerArea", new String[]{"AlphaBnd", "0", "0", "0", "AlphaBnd", "0", "0", "0", "AlphaBnd"});
    model.component("comp1").physics("solid").feature("tl3").feature("spm1").set("mPerVolume", "RhoBnd");
    model.component("comp1").physics("solid").feature("tl3").feature("spm1").selection().set(7, 8);

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").label("\u5f39\u7c27\u8fd1\u4f3c");
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").set("disabledphysics", new String[]{"solid/tl1", "solid/tl2"});
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "AlphaBnd", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "N/m^3", 0);
    model.study("std4").feature("stat").setIndex("pname", "AlphaBnd", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "N/m^3", 0);
    model.study("std4").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "range(0.1,0.1,0.5)", 0);
    model.study("std4").feature("stat").setIndex("pname", "AlphaBnd", 1);
    model.study("std4").feature("stat").setIndex("plistarr", "", 1);
    model.study("std4").feature("stat").setIndex("punit", "N/m^3", 1);
    model.study("std4").feature("stat").setIndex("pname", "AlphaBnd", 1);
    model.study("std4").feature("stat").setIndex("plistarr", "", 1);
    model.study("std4").feature("stat").setIndex("punit", "N/m^3", 1);
    model.study("std4").feature("stat").setIndex("pname", "AlphaRatio", 1);
    model.study("std4").feature("stat").setIndex("plistarr", "10^{range(-5,1,3)}", 1);
    model.study("std4").feature("stat").set("sweeptype", "filled");
    model.study("std4").feature("stat").set("pcontinuationmode", "manual");
    model.study("std4").feature("stat").set("pcontinuation", "stretch");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u5f39\u7c27\u8fd1\u4f3c");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5")
         .set("title", "\u4ece\u5f39\u7c27\u8fd1\u4f3c\u5230\u7406\u60f3\u754c\u9762\u7684\u8fc7\u6e21");
    model.result("pg5").set("paramindicator", "");
    model.result("pg5").set("view", "view3");
    model.result("pg5").set("plotarrayenable", true);
    model.result("pg5").set("arrayshape", "square");
    model.result("pg5").set("paddingsquare", "absolute");
    model.result("pg5").set("columnpadding", "L");
    model.result("pg5").set("rowpadding", "-2.4*L");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("arraydim", "2");
    model.result("pg5").feature("surf1").set("data", "dset4");
    model.result("pg5").feature("surf1").setIndex("looplevel", 3, 1);
    model.result("pg5").feature("surf1").set("expr", "solid.PxX");
    model.result("pg5").feature("surf1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").feature("surf2").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").setIndex("looplevel", 5, 1);
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature().duplicate("surf3", "surf2");
    model.result("pg5").feature("surf3").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").setIndex("looplevel", 6, 1);
    model.result("pg5").feature().duplicate("surf4", "surf3");
    model.result("pg5").feature("surf4").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf4").setIndex("looplevel", 7, 1);
    model.result("pg5").feature().duplicate("surf5", "surf4");
    model.result("pg5").feature("surf5").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf5").setIndex("looplevel", 9, 1);
    model.result("pg5").feature().duplicate("surf6", "surf5");
    model.result("pg5").feature("surf6").set("arraydim", "2");
    model.result("pg5").run();
    model.result("pg5").feature("surf6").set("data", "dset1");
    model.result("pg5").run();
    model.result("pg5").create("tlan1", "TableAnnotation");
    model.result("pg5").feature("tlan1").set("arraydim", "2");
    model.result("pg5").feature("tlan1").set("source", "localtable");
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 0, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "3/2*L", 0, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "$\\alpha_\\mathrm{ratio}$ = 0.001", 0, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "2*L", 1, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "3/2*L", 1, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "$\\alpha_\\mathrm{ratio}$ = 0.1", 1, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "4*L", 2, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "3/2*L", 2, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "$\\alpha_\\mathrm{ratio}$ = 1", 2, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "-3/2*L", 3, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "$\\alpha_\\mathrm{ratio}$ = 10", 3, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "2*L", 4, 0);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "-3/2*L", 4, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "$\\alpha_\\mathrm{ratio}$ = 1000", 4, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "4*L", 5, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "-3/2*L", 5, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\\unicode{\u7406\u60f3\u754c\u9762}", 5, 2);
    model.result("pg5").feature("tlan1").set("showpoint", false);
    model.result("pg5").feature("tlan1").set("latexmarkup", true);
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset4");
    model.result("pg6").set("xlabel", "\\alpha<sub>ratio</sub>");
    model.result("pg6").label("\u7ebf\u7ed3\u679c\u56fe\uff1a\u5f39\u7c27\u8fd1\u4f3c");
    model.result("pg6").set("legendpos", "lowerright");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("data", "dset4");
    model.result("pg6").feature("ptgr1").setIndex("legends", "\u5f39\u7c27\u8fd1\u4f3c", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("lnsg1").setIndex("xexpr", "1e-5", 1);
    model.result("pg6").feature("lnsg1").setIndex("xexpr", "1e3", 2);
    model.result("pg6").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/tl1", "solid/tl2", "solid/tl3"});
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/tl2", "solid/tl3"});
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/tl1", "solid/tl3"});

    model.result("pg2").run();

    model.title("\u8584\u5c42\u754c\u9762");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u7528\u4e8e\u63cf\u8ff0\u8584\u5c42\u7684\u66ff\u4ee3\u5b9e\u73b0\u65b9\u5f0f\uff0c\u4ee5\u53ca\u9009\u62e9\u5bf9\u4f4d\u79fb\u548c\u5e94\u529b\u573a\u8fde\u7eed\u6027\u7684\u5f71\u54cd\uff1b\u663e\u793a\u4e86\u5982\u4f55\u901a\u8fc7\u6e10\u8fdb\u5730\u66f4\u6539\u6750\u6599\u53c2\u6570\u6765\u83b7\u5f97\u7406\u60f3\u7684\u754c\u9762\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("thin_layer_interfaces.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
