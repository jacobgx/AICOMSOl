/*
 * ecm_microbore.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:59 by COMSOL 6.3.0.290. */
public class ecm_microbore {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials_with_Deforming_Geometries");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cd", "PrimaryCurrentDistribution", "geom1");

    model.component("comp1").multiphysics().create("ndbdg1", "NonDeformingBoundaryDeformedGeometry", 1);
    model.component("comp1").multiphysics("ndbdg1").set("Echem_physics", "cd");
    model.component("comp1").multiphysics("ndbdg1").selection().all();
    model.component("comp1").multiphysics().create("desdg1", "DeformingElectrodeSurfaceDeformedGeometry", 1);
    model.component("comp1").multiphysics("desdg1").set("Echem_physics", "cd");
    model.component("comp1").multiphysics("desdg1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/cd", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/desdg1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a1", "82.5[um]", "\u5b54\u5e95\u534a\u5f84");
    model.param().set("a2", "64[um]", "\u5b54\u9876\u534a\u5f84");
    model.param().set("sigma", "7[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("E_cell", "20[V]", "\u7535\u6c60\u7535\u538b");
    model.param().set("s_seed", "0.001[mm]", "\u53d8\u5f62\u79cd\u5b50\u5c42\u539a\u5ea6");
    model.param().set("M_Me", "54.94[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0c\u6eb6\u89e3\u7269\u8d28");
    model.param().set("rho_Me", "7.77[g/cm^3]", "\u5bc6\u5ea6\uff0c\u6eb6\u89e3\u7269\u8d28");
    model.param().set("n", "2.4", "\u5355\u4f4d\u6eb6\u89e3\u7269\u8d28\u7684\u7535\u5b50\u6570");
    model.param().set("i_threshold", "10[A/cm^2]", "\u6eb6\u89e3\u7684\u9608\u503c\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{3, 3});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -1});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.05, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.05, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.07, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.04, 3, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "a1", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", -1, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "a2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 3, 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 3, 3, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", -1, 3, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0.25, 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 1.25, 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 1, 1, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 3, 2, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 1, 2, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 3, 3, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("pol1", "pol2", "pol3");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"0.25+s_seed*3-a2", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "s_seed", 1);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"a2", "-s_seed"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("dif1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(6, 7, 10);
    model.component("comp1").selection("sel1").label("\u9633\u6781");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(3, 4);
    model.component("comp1").selection("sel2").label("\u9634\u6781");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").set(3);
    model.component("comp1").selection("sel3").label("\u9634\u6781\u5c16\u7aef");

    model.component("comp1").func().create("step1", "Step");

    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("cd").feature("es1").selection().named("sel1");
    model.component("comp1").physics("cd").feature("es1").set("phisext0", "E_cell");
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", "rho_Me", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", "M_Me", 0, 0);
    model.component("comp1").physics("cd").feature("es1")
         .set("SolveForDissolvingDepositingConcentrationVariable", false);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("nm", "n");
    model.component("comp1").physics("cd").feature("es1").feature("er1")
         .setIndex("Vib", "1*step1(cd.itot/i_threshold-1)", 0, 0);
    model.component("comp1").physics("cd").create("es2", "ElectrodeSurface", 1);
    model.component("comp1").physics("cd").feature("es2").selection().named("sel2");

    model.component("comp1").multiphysics("ndbdg1").set("BoundaryCondition", "ZeroNormalDisplacement");
    model.component("comp1").multiphysics("desdg1").selection().set(1, 2, 5, 6, 7, 8, 9, 10, 11, 12);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("autoremesh", true);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").label("\u91cd\u65b0\u5212\u5206\u7f51\u683c\u540e\u7684\u89e3 1");
    model.sol("sol2").study("std1");
    model.sol("sol1").feature("t1").feature("arDef").set("tadapsol", "sol2");
    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset2");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 17, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").setIndex("looplevel", 17, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d, 3D (cd)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilphi", "cd.Ilz"});
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 17, 0);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilz"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 17, 0);
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6, 3D (cd)");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg4").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilphi", "cd.Ilz"});
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").feature("str1").create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("inherittubescale", false);
    model.result("pg3").feature("line1").set("inheritplot", "str1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg4").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 17, 0);
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (cd)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "rev1");
    model.result("pg6").setIndex("looplevel", 17, 0);
    model.result("pg6").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d, 3D (cd)");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"cd.phisext"});
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("inherittubescale", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cd.phisext"});
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 17, 0);
    model.result("pg7").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "rev1");
    model.result("pg8").setIndex("looplevel", 17, 0);
    model.result("pg8").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d, 3D (cd)");
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilz"});
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("recover", "pprint");
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg7").feature("str1").set("color", "gray");
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg8").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilphi", "cd.Ilz"});
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg7").feature("line1").set("linetype", "tube");
    model.result("pg7").feature("line1").set("inherittubescale", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"cd.Evsref"});
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevel", 17, 0);
    model.result("pg9").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg9").create("mesh1", "Mesh");
    model.result("pg9").feature("mesh1").set("meshdomain", "surface");
    model.result("pg9").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg9").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg9").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg9").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg9").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg9").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg9").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").set("posmethod", "magnitude");
    model.result("pg1").feature("str1").set("mdist", new String[]{"0.0010", "0.030"});
    model.result("pg1").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u5b54\u5f62\u72b6");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr1").set("linewidth", "preference");
    model.result("pg10").feature("lngr1").set("data", "dset2");
    model.result("pg10").feature("lngr1").setIndex("looplevelinput", "first", 0);
    model.result("pg10").feature("lngr1").selection().named("sel1");
    model.result("pg10").feature("lngr1").set("expr", "z");
    model.result("pg10").feature("lngr1").set("xdata", "expr");
    model.result("pg10").feature("lngr1").set("xdataexpr", "r");
    model.result("pg10").feature("lngr1").set("legend", true);
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("lngr2", "lngr1");
    model.result("pg10").run();
    model.result("pg10").feature("lngr2").setIndex("looplevelinput", "interp", 0);
    model.result("pg10").feature("lngr2").setIndex("interp", 0.5, 0);
    model.result("pg10").run();
    model.result("pg10").feature().duplicate("lngr3", "lngr2");
    model.result("pg10").run();
    model.result("pg10").feature("lngr3").setIndex("interp", 1, 0);
    model.result("pg10").feature().duplicate("lngr4", "lngr3");
    model.result("pg10").run();
    model.result("pg10").feature("lngr4").set("linecolor", "black");
    model.result("pg10").feature("lngr4").set("legend", false);
    model.result("pg10").run();
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "t=0 s\u30010.5 s \u548c 1 s \u65f6\u7684\u5b54\u5f62\u72b6");
    model.result("pg10").set("axislimits", true);
    model.result("pg10").set("xmin", 0);
    model.result("pg10").set("xmax", 0.3);
    model.result("pg10").set("ymin", -0.2);
    model.result("pg10").set("ymax", 0.01);
    model.result("pg10").set("legendpos", "lowerright");
    model.result("pg10").run();
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().geom("geom1", 1);
    model.result().dataset("dset3").selection().named("sel1");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset3");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").label("\u5b54\u8868\u9762\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg11").set("data", "rev2");
    model.result("pg11").set("edges", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "cd.itot");
    model.result("pg11").feature("surf1").set("descr", "\u754c\u9762\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg11").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg11");
    model.result().export("anim1").set("looplevelinput", "interp");
    model.result().export("anim1").set("interp", "range(0,0.1,1)");
    model.result().export("anim1").run();

    model.title("\u5fae\u5b54\u7684\u7535\u5316\u5b66\u52a0\u5de5");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u901a\u8fc7\u7535\u5316\u5b66\u52a0\u5de5 (ECM) \u53bb\u9664\u91d1\u5c5e\u7684\u8fc7\u7a0b\uff0c\u5176\u4e2d\u5c06\u4e00\u6b21\u7535\u6d41\u5206\u5e03\u6a21\u578b\u4e0e\u7535\u6d41\u5bc6\u5ea6\u9608\u503c\u6761\u4ef6\u7ed3\u5408\u4f7f\u7528\uff0c\u4f4e\u4e8e\u8be5\u9608\u503c\u65f6\u4e0d\u53d1\u751f\u6750\u6599\u53bb\u9664\u3002\u6a21\u578b\u51e0\u4f55\u4e3a\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u7ed3\u6784\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("ecm_microbore.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
