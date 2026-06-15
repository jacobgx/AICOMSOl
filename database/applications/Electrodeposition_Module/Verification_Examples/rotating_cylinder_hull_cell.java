/*
 * rotating_cylinder_hull_cell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:00 by COMSOL 6.3.0.290. */
public class rotating_cylinder_hull_cell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cd", "PrimaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("sigma", "0.35[S/cm]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("i_app", "-100[A/m^2]", "\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("H", "8[cm]", "\u5de5\u4f5c\u7535\u6781\u957f\u5ea6");
    model.param().set("i0", "5.37e-5[A/cm^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Ac", "-0.0525*2.301[V]", "Tafel \u659c\u7387");
    model.param().set("cb", "5e-5[mol/cm^3]", "\u94dc\u79bb\u5b50\u672c\u4f53\u6d53\u5ea6");
    model.param().set("D", "4.2e-6[cm^2/s]", "\u94dc\u79bb\u5b50\u6269\u6563\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("table", new String[][]{{"0", "-7.5e-2"}, 
         {"5.5e-2", "-7.5e-2"}, 
         {"5.5e-2", "12.5e-2"}, 
         {"2.5e-2", "12.5e-2"}, 
         {"2.5e-2", "0"}, 
         {"2.6e-2", "0"}, 
         {"2.7e-2", "0"}, 
         {"2.7e-2", "-2.5e-2"}, 
         {"2.6e-2", "-2.5e-2"}, 
         {"2.2e-2", "-2.5e-2"}, 
         {"2.2e-2", "12.5e-2"}, 
         {"1.15e-2", "12.5e-2"}, 
         {"1.15e-2", "H"}, 
         {"0.3e-2", "H"}, 
         {"0.3e-2", "0"}, 
         {"1.15e-2", "0"}, 
         {"1.15e-2", "-2.5e-2"}, 
         {"0", "-2.5e-2"}, 
         {"0", "-7.5e-2"}});
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"30e-6", "H"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0.003, 0});
    model.component("comp1").geom("geom1").feature("r1").label("\u8fb9\u754c\u5c42");
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").run("r1");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("i_analytical", "(0.535-0.458*z/H)/sqrt(0.0233+(z/H)^2)+8.52e-005*exp(7.17*z/H)[1]", "\u5f52\u4e00\u5316\u89e3\u6790\u7535\u6d41\u5bc6\u5ea6\u8868\u8fbe\u5f0f");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(4);
    model.component("comp1").selection("sel1").label("\u5de5\u4f5c\u7535\u6781");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(18, 19, 20);
    model.component("comp1").selection("sel2").label("\u5bf9\u7535\u6781");

    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cd").create("eip1", "ElectrolytePotential", 1);
    model.component("comp1").physics("cd").feature("eip1").selection().named("sel2");
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("cd").feature("es1").selection().named("sel1");
    model.component("comp1").physics("cd").feature("es1").set("BoundaryCondition", "AverageCurrentDensity");
    model.component("comp1").physics("cd").feature("es1").set("Ial", "i_app");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_r1_dom");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(5, 6);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(4, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 200);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
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
    model.result("pg3").set("data", "dset1");
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
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (cd)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "rev1");
    model.result("pg6").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d, 3D (cd)");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"cd.phisext"});
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("inherittubescale", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cd.phisext"});
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "rev1");
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
    model.result("pg1").run();

    model.sol("sol1").copySolution("sol2");
    model.sol("sol2").label("\u4e00\u6b21");

    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("data", "dset2");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").selection().named("sel1");
    model.result("pg9").feature("lngr1").set("expr", "cd.iloc_er1/i_app");
    model.result("pg9").feature("lngr1").set("descractive", true);
    model.result("pg9").feature("lngr1").set("descr", "\u5f52\u4e00\u5316\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "z");
    model.result("pg9").feature("lngr1").set("xdatadescractive", true);
    model.result("pg9").feature("lngr1").set("xdatadescr", "\u6cbf\u5de5\u4f5c\u7535\u6781\u7684\u8ddd\u79bb");
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").feature("lngr1").set("legendmethod", "manual");
    model.result("pg9").feature("lngr1").setIndex("legends", "\u6570\u503c", 0);
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("lngr2", "lngr1");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").set("expr", "i_analytical");
    model.result("pg9").feature("lngr2").set("titletype", "none");
    model.result("pg9").feature("lngr2").setIndex("legends", "\u89e3\u6790\u503c", 0);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").label("\u5f52\u4e00\u5316\u7535\u6d41\u5bc6\u5ea6\uff0c\u4e00\u6b21");

    model.component("comp1").physics("cd").prop("CurrentDistributionType")
         .set("CurrentDistributionType", "Secondary");
    model.component("comp1").physics("cd").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0", "i0");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Ac", "Ac");

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "sigma", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "S/m", 0);
    model.study("std1").feature("stat").setIndex("pname", "sigma", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "S/m", 0);
    model.study("std1").feature("stat").setIndex("pname", "i_app", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "-5, -10, -20, -40, -60, -80, -100, -140, -180", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.sol("sol1").copySolution("sol3");
    model.sol("sol3").label("\u4e8c\u6b21");

    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").set("data", "dset3");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr1").set("linewidth", "preference");
    model.result("pg10").feature("lngr1").selection().named("sel1");
    model.result("pg10").feature("lngr1").set("expr", "cd.eta_er1");
    model.result("pg10").feature("lngr1").set("descr", "\u8fc7\u7535\u4f4d");
    model.result("pg10").feature("lngr1").set("descractive", true);
    model.result("pg10").feature("lngr1").set("xdata", "expr");
    model.result("pg10").feature("lngr1").set("xdataexpr", "z");
    model.result("pg10").feature("lngr1").set("xdatadescractive", true);
    model.result("pg10").feature("lngr1").set("xdatadescr", "\u6cbf\u5de5\u4f5c\u7535\u6781\u7684\u8ddd\u79bb");
    model.result("pg10").feature("lngr1").set("legend", true);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").label("\u8fc7\u7535\u4f4d\uff0c\u4e8c\u6b21");
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").feature("lngr1").set("expr", "cd.iloc_er1/i_app");
    model.result("pg11").feature("lngr1").set("descr", "\u5f52\u4e00\u5316\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").label("\u5f52\u4e00\u5316\u7535\u6d41\u5bc6\u5ea6\uff0c\u4e8c\u6b21");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

    model.component("comp1").physics("tds").selection().named("geom1_r1_dom");
    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "cb", 0);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(7);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "cb", 0);
    model.component("comp1").physics("tds").create("eeic1", "ElectrodeElectrolyteInterfaceCoupling", 1);
    model.component("comp1").physics("tds").feature("eeic1").selection().named("sel1");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1")
         .set("iloc_src", "root.comp1.cd.es1.er1.iloc");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").set("nm", 2);
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").setIndex("Vib", -1, 0);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0", "i0*(c/cb)");

    model.study("std1").feature("stat").setIndex("plistarr", "-1, -5, -10, -20, -40, -60, -80, -100, -130", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").selection().named("sel1");
    model.result("pg12").feature("lngr1").set("expr", "cd.eta_er1");
    model.result("pg12").feature("lngr1").set("descr", "\u8fc7\u7535\u4f4d");
    model.result("pg12").feature("lngr1").set("descractive", true);
    model.result("pg12").feature("lngr1").set("xdata", "expr");
    model.result("pg12").feature("lngr1").set("xdataexpr", "z");
    model.result("pg12").feature("lngr1").set("xdatadescractive", true);
    model.result("pg12").feature("lngr1").set("xdatadescr", "\u6cbf\u5de5\u4f5c\u7535\u6781\u7684\u8ddd\u79bb");
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").label("\u8fc7\u7535\u4f4d\uff0c\u4e09\u6b21");
    model.result().duplicate("pg13", "pg12");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").feature("lngr1").set("expr", "cd.iloc_er1");
    model.result("pg13").feature("lngr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").label("\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6\uff0c\u4e09\u6b21");
    model.result().duplicate("pg14", "pg13");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").feature("lngr1").set("expr", "c/cb");
    model.result("pg14").feature("lngr1").set("descr", "\u5f52\u4e00\u5316\u6d53\u5ea6");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").label("\u5f52\u4e00\u5316\u6d53\u5ea6\uff0c\u4e09\u6b21");
    model.result().create("pg15", "PlotGroup1D");
    model.result("pg15").run();
    model.result("pg15").set("data", "none");
    model.result("pg15").create("lngr1", "LineGraph");
    model.result("pg15").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg15").feature("lngr1").set("linewidth", "preference");
    model.result("pg15").feature("lngr1").set("data", "dset2");
    model.result("pg15").feature("lngr1").selection().named("sel1");
    model.result("pg15").feature("lngr1").set("expr", "cd.iloc_er1/i_app");
    model.result("pg15").feature("lngr1").set("descractive", true);
    model.result("pg15").feature("lngr1").set("descr", "\u5f52\u4e00\u5316\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg15").feature("lngr1").set("xdata", "expr");
    model.result("pg15").feature("lngr1").set("xdataexpr", "z");
    model.result("pg15").feature("lngr1").set("xdatadescractive", true);
    model.result("pg15").feature("lngr1").set("xdatadescr", "\u6cbf\u5de5\u4f5c\u7535\u6781\u7684\u8ddd\u79bb");
    model.result("pg15").feature("lngr1").set("legend", true);
    model.result("pg15").feature("lngr1").set("legendmethod", "manual");
    model.result("pg15").feature("lngr1").setIndex("legends", "\u4e00\u6b21", 0);
    model.result("pg15").run();
    model.result("pg15").feature().duplicate("lngr2", "lngr1");
    model.result("pg15").run();
    model.result("pg15").feature("lngr2").set("data", "dset3");
    model.result("pg15").feature("lngr2").setIndex("looplevelinput", "manual", 0);
    model.result("pg15").feature("lngr2").setIndex("looplevel", new int[]{7}, 0);
    model.result("pg15").feature("lngr2").set("titletype", "none");
    model.result("pg15").feature("lngr2").setIndex("legends", "\u4e8c\u6b21", 0);
    model.result("pg15").run();
    model.result("pg15").feature().duplicate("lngr3", "lngr2");
    model.result("pg15").run();
    model.result("pg15").feature("lngr3").set("data", "dset1");
    model.result("pg15").feature("lngr3").setIndex("looplevel", new int[]{8}, 0);
    model.result("pg15").feature("lngr3").setIndex("legends", "\u4e09\u6b21", 0);
    model.result("pg15").run();
    model.result("pg15").run();
    model.result("pg15").label("\u7535\u6d41\u5bc6\u5ea6\u6bd4\u8f83");
    model.result("pg2").run();

    model.title("\u65cb\u8f6c\u5706\u7b52\u8d6b\u5c14\u69fd");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u793a\u4f8b\uff0c\u7528\u4e8e\u6a21\u62df\u548c\u6bd4\u8f83\u65cb\u8f6c\u5706\u7b52\u8d6b\u5c14\u69fd\u4e2d\u7684\u4e00\u6b21\u3001\u4e8c\u6b21\u548c\u4e09\u6b21\u7535\u6d41\u5206\u5e03\u3002");

    model.label("rotating_cylinder_hull_cell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
