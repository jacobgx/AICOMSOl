/*
 * cu_trench_deposition.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:59 by COMSOL 6.3.0.290. */
public class cu_trench_deposition {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials_with_Deforming_Geometries");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cCu");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cCu", "cSO4"});

    model.component("comp1").multiphysics().create("ndbdg1", "NonDeformingBoundaryDeformedGeometry", 1);
    model.component("comp1").multiphysics("ndbdg1").set("Echem_physics", "tcd");
    model.component("comp1").multiphysics("ndbdg1").selection().all();
    model.component("comp1").multiphysics().create("desdg1", "DeformingElectrodeSurfaceDeformedGeometry", 1);
    model.component("comp1").multiphysics("desdg1").set("Echem_physics", "tcd");
    model.component("comp1").multiphysics("desdg1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().all();

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("cdi").setSolveFor("/multiphysics/desdg1", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/desdg1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Cinit", "500[mol/(m^3)]", "\u521d\u59cb\u6d53\u5ea6");
    model.param().set("T0", "298[K]", "\u7cfb\u7edf\u6e29\u5ea6");
    model.param().set("i0_ref", "250[A/m^2]", "\u53c2\u8003\u6761\u4ef6\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("phis_anode", "0.135[V]", "\u9633\u6781\u7535\u4f4d");
    model.param().set("phis_cathode", "-0.135[V]", "\u9634\u6781\u7535\u4f4d");
    model.param().set("alpha_a", "1.5[1]", "\u5bf9\u79f0\u56e0\u5b50");
    model.param().set("z_Cu", "2[1]", "\u7535\u8377\uff0c\u7269\u8d28 Cu");
    model.param().set("z_SO4", "-2[1]", "\u7535\u8377\uff0c\u7269\u8d28 SO4");
    model.param().set("D_Cu", "2e-9[m^2/s]", "\u6269\u6563\u7cfb\u6570\uff0c\u7269\u8d28 Cu");
    model.param().set("D_SO4", "D_Cu", "\u6269\u6563\u7cfb\u6570\uff0c\u7269\u8d28 SO4");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"1.6e-5", "3e-5"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-0.8e-5", "1e-5"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"0.4e-5", "1e-5"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-0.2e-5", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");

    model.component("comp1").view("view1").set("showlabels", true);

    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("uni1", 3, 4, 5, 6);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "1e-6");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_Cu", 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", "z_SO4", 1);
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cCu", new String[]{"D_Cu", "0", "0", "0", "D_Cu", "0", "0", "0", "D_Cu"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cSO4", new String[]{"D_SO4", "0", "0", "0", "D_SO4", "0", "0", "0", "D_SO4"});
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es1").selection().set(3);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").label("\u9633\u6781");
    model.component("comp1").selection("sel1").set(3);

    model.component("comp1").physics("tcd").feature("es1").selection().named("sel1");
    model.component("comp1").physics("tcd").feature("es1").set("phisext0", "phis_anode");
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "cdep_anode", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0_ref");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", "alpha_a");
    model.component("comp1").physics("tcd").feature().duplicate("es2", "es1");
    model.component("comp1").physics("tcd").feature("es2").selection().set(2, 4, 5, 6, 7, 9, 10, 11, 12);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").label("\u9634\u6781");
    model.component("comp1").selection("sel2").set(2, 4, 5, 6, 7, 9, 10, 11, 12);

    model.component("comp1").physics("tcd").feature("es2").selection().named("sel2");
    model.component("comp1").physics("tcd").feature("es2").set("phisext0", "phis_cathode");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "Cinit", 1);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T0"}});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "5e-7");
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.5,5)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("recover", "pprint");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("inherittubescale", false);
    model.result("pg2").feature("line1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"tcd.phisext"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("inherittubescale", false);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (tcd)");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("recover", "pprint");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"tcd.Evsref"});
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("inherittubescale", false);
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").label("\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316 (tcd)");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"tcd.sbtot"});
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("inherittubescale", false);
    model.result("pg5").feature("line1").set("unit", "\u00b5m");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").label("\u6d53\u5ea6, Cu (tcd)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cCu"});
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").set("typeintitle", true);
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"tcd.tflux_cCux", "tcd.tflux_cCuy"});
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("recover", "pprint");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 11, 0);
    model.result("pg7").label("\u6d53\u5ea6, SO4 (tcd)");
    model.result("pg7").set("titletype", "custom");
    model.result("pg7").set("prefixintitle", "");
    model.result("pg7").set("expressionintitle", false);
    model.result("pg7").set("typeintitle", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"cSO4"});
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").set("typeintitle", true);
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"tcd.tflux_cSO4x", "tcd.tflux_cSO4y"});
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("recover", "pprint");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").setIndex("looplevel", 11, 0);
    model.result("pg8").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg8").create("mesh1", "Mesh");
    model.result("pg8").feature("mesh1").set("meshdomain", "surface");
    model.result("pg8").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg8").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg8").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg8").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg8").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg8").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg8").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg8").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg6").run();
    model.result("pg6").create("con1", "Contour");
    model.result("pg6").feature("con1").set("expr", "cCu");
    model.result("pg6").feature("con1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccCu");
    model.result("pg6").feature("con1").set("coloring", "uniform");
    model.result("pg6").feature("con1").set("color", "black");
    model.result("pg6").feature("con1").set("colorlegend", false);
    model.result("pg6").run();
    model.result("pg6").feature("str1").set("posmethod", "selection");
    model.result("pg6").feature("str1").selection().named("sel1");
    model.result("pg6").feature("str1").set("selnumber", 15);
    model.result("pg6").run();
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("data", "dset1");
    model.result("pg6").feature("line1").setIndex("looplevel", 1, 0);
    model.result("pg6").feature("line1").set("coloring", "uniform");
    model.result("pg6").feature("line1").set("color", "black");
    model.result("pg6").feature("line1").set("titletype", "none");
    model.result("pg6").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u9540\u5c42\u539a\u5ea6");
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "\u5782\u76f4\u58c1\u4e0a\u6c89\u79ef\u5c42\u8f6e\u5ed3");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u5230\u6c9f\u69fd\u5e95\u90e8\u7684\u8ddd\u79bb (\\mu m)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u6c89\u79ef\u5c42\u7684\u539a\u5ea6 (\\mu m)");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").selection().set(4);
    model.result("pg9").feature("lngr1").set("expr", "x-Xg");
    model.result("pg9").feature("lngr1").set("unit", "\u00b5m");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "y");
    model.result("pg9").feature("lngr1").set("xdataunit", "\u00b5m");
    model.result("pg9").run();

    model.study().create("std2");
    model.study("std2").create("cdi", "CurrentDistributionInitialization");
    model.study("std2").feature("cdi").set("ftplistmethod", "manual");
    model.study("std2").feature("cdi").set("solnum", "auto");
    model.study("std2").feature("cdi").set("notsolnum", "auto");
    model.study("std2").feature("cdi").set("outputmap", new String[]{});
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").setSolveFor("/physics/tcd", true);
    model.study("std2").feature("cdi").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std2").feature("cdi").setSolveFor("/multiphysics/desdg1", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/tcd", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/desdg1", true);
    model.study("std2").feature("time").set("tlist", "range(0,1,14)");
    model.study("std2").feature("time").set("autoremesh", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").label("\u91cd\u65b0\u5212\u5206\u7f51\u683c\u540e\u7684\u89e3 1");
    model.sol("sol5").study("std2");
    model.sol("sol3").feature("t1").feature("arDef").set("tadapsol", "sol5");
    model.sol("sol3").runAll();

    model.result("pg6").run();
    model.result("pg6").set("data", "dset5");
    model.result("pg6").set("looplevel", new String[]{"interp"});
    model.result("pg6").set("interp", new int[]{14});
    model.result("pg6").run();

    model.title("\u6c9f\u69fd\u4e2d\u9540\u94dc");

    model
         .description("\u672c\u4f8b\u9610\u8ff0\u5982\u4f55\u6a21\u62df\u5f53\u9634\u6781\u8868\u9762\u53d1\u751f\u7535\u9540\u94dc\u65f6\u7684\u53d8\u5f62\u51e0\u4f55\u3002\u5176\u4e2d\u8003\u8651\u4e86\u6d53\u5ea6\u76f8\u5173\u7684\u7535\u6781\u52a8\u529b\u5b66\u4ee5\u53ca\u6269\u6563\u548c\u8fc1\u79fb\u5f15\u8d77\u7684\u79bb\u5b50\u4f20\u8f93\uff0c\u56e0\u6b64\uff0c\u8fd9\u662f\u4e00\u4e2a\u4e09\u6b21\u7535\u6d41\u5bc6\u5ea6\u5206\u5e03\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("cu_trench_deposition.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
