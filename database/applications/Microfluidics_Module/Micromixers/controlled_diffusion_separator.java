/*
 * controlled_diffusion_separator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:09 by COMSOL 6.3.0.290. */
public class controlled_diffusion_separator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Micromixers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "CreepingFlow", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{140, 60});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new int[]{120, 50});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new int[]{10, 10});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("dif1", 3, 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("point")
         .set("fil1", 1, 9);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", 20);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("fil2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("fil2", "mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 10, 0);
    model.component("comp1").geom("geom1").run("ext1");

    model.param().set("D", "5e-11[m^2/s]");
    model.param().descr("D", "\u6269\u6563\u5e38\u6570");
    model.param().set("fr", "15[pl/s]");
    model.param().descr("fr", "\u5165\u53e3\u6d41\u7387");
    model.param().set("c0", "1[mol/m^3]");
    model.param().descr("c0", "\u5165\u53e3\u6d53\u5ea6");
    model.param().set("alpha", "0.5[(m^3/mol)^2]");
    model.param().descr("alpha", "\u9ecf\u5ea6 c^2 \u9879\u524d\u56e0\u5b50");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"1e-3"});

    model.component("comp1").multiphysics().create("rfd1", "ReactingFlowDS", 3);

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2, 10);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("FullyDevelopedFlowOption", "V0");
    model.component("comp1").physics("spf").feature("inl1").set("V0fdf", "fr/2");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(23, 25);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().set(4, 9);
    model.component("comp1").physics("tds").prop("ShapeProperty").set("order_concentration", 2);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").create("conc1", "Concentration", 2);
    model.component("comp1").physics("tds").feature("conc1").selection().set(2);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c0", 0);
    model.component("comp1").physics("tds").create("conc2", "Concentration", 2);
    model.component("comp1").physics("tds").feature("conc2").selection().set(10);
    model.component("comp1").physics("tds").feature("conc2").setIndex("species", true, 0);
    model.component("comp1").physics("tds").create("out1", "Outflow", 2);
    model.component("comp1").physics("tds").feature("out1").selection().set(23);
    model.component("comp1").physics("tds").create("out2", "Outflow", 2);
    model.component("comp1").physics("tds").feature("out2").selection().set(25);

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size2").selection()
         .set(1, 3, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24, 26, 27);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").selection()
         .set(1, 3, 5, 6, 7, 8, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24, 26, 27);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat2").set("useparam", true);
    model.study("std1").feature("stat2").setIndex("pname", "D", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "m^2/s", 0);
    model.study("std1").feature("stat2").setIndex("pname", "D", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat2").setIndex("punit", "m^2/s", 0);
    model.study("std1").feature("stat2").setIndex("plistarr", "1e-10 5e-11 1e-11", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection()
         .set(1, 3, 5, 6, 7, 8, 11, 12, 13, 14, 15, 17, 18, 19, 20, 21, 22, 24, 26, 27);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u901f\u5ea6\uff08\u975e\u8026\u5408\u6d41\u52a8\uff09");
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("unit", "mm/s");
    model.result("pg1").run();
    model.result("pg1").create("slc2", "Slice");
    model.result("pg1").feature("slc2").set("unit", "mm/s");
    model.result("pg1").feature("slc2").set("quickplane", "xy");
    model.result("pg1").feature("slc2").set("quickznumber", 1);
    model.result("pg1").feature("slc2").set("inheritplot", "slc1");
    model.result("pg1").feature("slc2").set("titletype", "none");
    model.result("pg1").feature().duplicate("slc3", "slc2");
    model.result("pg1").run();
    model.result("pg1").feature("slc3").set("quickplane", "zx");
    model.result("pg1").feature("slc3").set("quickynumber", 2);
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("xnumber", 14);
    model.result("pg1").feature("arwv1").set("ynumber", 21);
    model.result("pg1").feature("arwv1").set("znumber", 3);
    model.result("pg1").feature("arwv1").set("color", "black");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u538b\u529b\uff08\u975e\u8026\u5408\u6d41\u52a8\uff09");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("descr", "\u538b\u529b");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u6d53\u5ea6\uff08\u975e\u8026\u5408\u6d41\u52a8\uff09");
    model.result("pg3").run();
    model.result("pg3").feature("slc1").set("expr", "c");
    model.result("pg3").feature("slc1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg3").run();
    model.result("pg3").feature("slc2").set("expr", "c");
    model.result("pg3").feature("slc2").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg3").run();
    model.result("pg3").feature("slc3").set("expr", "c");
    model.result("pg3").feature("slc3").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg3").run();
    model.result("pg3").feature("arwv1").active(false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").stepPrevious(0);
    model.result("pg3").run();
    model.result("pg3").stepPrevious(0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u51fa\u53e3\u6d53\u5ea6\uff08\u975e\u8026\u5408\u6d41\u52a8\uff09");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"tds.out2.c0_avg_c"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u5e73\u5747\u6d53\u5ea6"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").feature("glob1").set("linemarker", "point");
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "D");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u6269\u6563\u7cfb\u6570 (m<sup>2</sup>/s)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u6d41\u4f53 B \u51fa\u53e3\u7684\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("ymin", 0);
    model.result("pg4").set("ymax", 0.5);
    model.result("pg4").run();

    model.component("comp1").physics("spf").feature("fp1").label("\u6d41\u4f53\u5c5e\u6027\uff1a\u65e0\u8026\u5408");
    model.component("comp1").physics("spf").feature().duplicate("fp2", "fp1");
    model.component("comp1").physics("spf").feature("fp2").label("\u6d41\u4f53\u5c5e\u6027\uff1a\u8026\u5408");
    model.component("comp1").physics("spf").feature("fp2").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp2").set("mu", "1e-3[Pa*s]*(1+alpha*c^2)");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"spf/fp2"});
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/tds", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/rfd1", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().duplicate("surf2", "surf1");
    model.result().dataset("surf2").set("data", "dset3");
    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").label("\u901f\u5ea6\uff08\u8026\u5408\u6d41\u52a8\uff09");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").run();
    model.result("pg2").run();
    model.result().duplicate("pg6", "pg2");
    model.result("pg6").run();
    model.result("pg6").label("\u538b\u529b\uff08\u8026\u5408\u6d41\u52a8\uff09");
    model.result("pg6").set("data", "surf2");
    model.result("pg6").run();
    model.result("pg3").run();
    model.result().duplicate("pg7", "pg3");
    model.result("pg7").run();
    model.result("pg7").label("\u6d53\u5ea6\uff08\u8026\u5408\u6d41\u52a8\uff09");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickx", 70);
    model.result().dataset().duplicate("cpl2", "cpl1");
    model.result().dataset("cpl2").set("data", "dset3");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u901f\u5ea6\u6bd4\u8f83");
    model.result("pg8").set("data", "cpl1");
    model.result("pg8").setIndex("looplevel", 2, 0);
    model.result("pg8").create("con1", "Contour");
    model.result("pg8").feature("con1").set("number", 5);
    model.result("pg8").feature().duplicate("con2", "con1");
    model.result("pg8").run();
    model.result("pg8").feature("con2").set("data", "cpl2");
    model.result("pg8").feature("con2").set("titletype", "none");
    model.result("pg8").feature("con2").set("colorlegend", false);
    model.result("pg8").feature("con2").set("inheritplot", "con1");
    model.result("pg8").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"tds.out2.c0_avg_c"});
    model.result().numerical("gev1").set("descr", new String[]{"\u5e73\u5747\u6d53\u5ea6"});
    model.result().numerical("gev1").set("unit", new String[]{"mol/m^3"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").set("data", "dset3");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();

    model.title("\u53ef\u63a7\u6269\u6563\u5206\u79bb\u5668");

    model
         .description("\u672c\u6a21\u578b\u5904\u7406\u4e00\u79cd\u901a\u8fc7\u6269\u6563\u8fdb\u884c\u53ef\u63a7\u5206\u79bb\u7684 H \u578b\u5fae\u6d41\u4f53\u88c5\u7f6e\u3002\u8be5\u88c5\u7f6e\u4f7f\u4e24\u4e2a\u4e0d\u540c\u7684\u5c42\u6d41\u5728\u4e00\u6bb5\u53d7\u63a7\u7684\u65f6\u95f4\u5185\u53d1\u751f\u63a5\u89e6\u3002\u63a5\u89e6\u9762\u5df2\u660e\u786e\u5b9a\u4e49\uff0c\u901a\u8fc7\u63a7\u5236\u6d41\u7387\u53ef\u4ee5\u4fdd\u6301\u5c42\u6d41\u573a\uff0c\u5e76\u63a7\u5236\u901a\u8fc7\u6269\u6563\u4ece\u4e00\u79cd\u6d41\u4f53\u4f20\u9012\u5230\u53e6\u4e00\u79cd\u6d41\u4f53\u7684\u7269\u8d28\u6570\u91cf\u3002\u672c\u4f8b\u6700\u521d\u7531\u897f\u96c5\u56fe\u534e\u76db\u987f\u5927\u5b66\u7684 Albert Witarsa \u5728 Bruce Finlayson \u6559\u6388\u7684\u6307\u5bfc\u4e0b\u63d0\u51fa\uff0c\u8fd9\u9879\u5de5\u4f5c\u4f5c\u4e3a\u7814\u7a76\u751f\u8bfe\u7a0b\u4f5c\u4e1a\u7684\u4e00\u90e8\u5206\uff0c\u76ee\u7684\u662f\u901a\u8fc7\u6570\u5b66\u5efa\u6a21\u8bc4\u4f30\u5fae\u6d41\u4f53\u4e13\u5229\u7684\u6f5c\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("controlled_diffusion_separator.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
