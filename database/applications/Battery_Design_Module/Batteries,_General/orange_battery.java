/*
 * orange_battery.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class orange_battery {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Batteries,_General");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "5e-2");
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u950c\u9489");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "2e-3");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "5e-2");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-2e-2", "0", "2e-2"});
    model.component("comp1").geom("geom1").feature("cyl1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("cyl1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").label("\u94dc\u9489");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"2e-2", "0", "2e-2"});

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("i_app", "1e-3[A]", "\u5916\u52a0\u7535\u6d41");
    model.param().set("sigma", "0.35[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("T", "293.15[K]", "\u7535\u6c60\u6e29\u5ea6");
    model.param().set("c_ref", "1[mol/l]", "\u53c2\u8003\u6d53\u5ea6");
    model.param().set("c_H0", "10^-4[mol/l]", "\u8d28\u5b50\u6d53\u5ea6\uff0cpH 4");
    model.param().set("c_Zn20", "1e-6[mol/l]", "\u521d\u59cb\u950c\u79bb\u5b50\u6d53\u5ea6");
    model.param()
         .set("E_eq_Zn0", "-0.76[V]+R_const*T/(2*F_const)*log(c_Zn20/c_ref)", "\u521d\u59cb\u950c\u7535\u6781\u7684\u5e73\u8861\u7535\u4f4d");
    model.param().set("alpha_c_Zn", "1.5", "\u9634\u6781\u4f20\u9012\u7cfb\u6570\uff0cZn \u53cd\u5e94");
    model.param().set("alpha_a_Zn", "2-alpha_c_Zn", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0cZn \u53cd\u5e94");
    model.param()
         .set("i0_H2", "10[A/m^2]", "\u94dc\u7535\u6781\u4e0a\u6790\u6c22\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Eeqref_Zn", "-0.76[V]", "\u950c\u7535\u6781\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param().set("i0ref_Zn", "10[A/m^2]", "\u950c\u7535\u6781\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("c_Zn2", "c_Zn20", "\u950c\u79bb\u5b50\u6d53\u5ea6\uff08\u7528\u4e8e\u4e0a\u8ff0\u8868\u8fbe\u5f0f\uff09");

    model.component("comp1").physics("cd").selection().set(1);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(3);
    model.component("comp1").selection("sel1").label("\u6a59\u5b50");
    model.component("comp1").selection("sel1").set(1);

    model.component("comp1").physics("cd").selection().named("sel1");
    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es1").selection().named("geom1_cyl1_bnd");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Eeq_mat", "NernstEquation");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Eeq_ref", "Eeqref_Zn");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("CONernst", "c_Zn2/c_ref");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0Type", "FromNernstEquation");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0_ref", "i0ref_Zn");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("alphaa", "alpha_a_Zn");
    model.component("comp1").physics("cd").create("es2", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es2").selection().named("geom1_cyl2_bnd");
    model.component("comp1").physics("cd").feature("es2").set("BoundaryCondition", "TotalCurrent");
    model.component("comp1").physics("cd").feature("es2").set("Itl", "-i_app");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("Eeq_mat", "NernstEquation");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("CONernst", "c_H0/c_ref");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("i0", "i0_H2");
    model.component("comp1").physics("cd").feature("init1").set("phil", "-E_eq_Zn0");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").autoMeshSize(6);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg2").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (cd)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cd.phisext"});
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u4f4d\u7b49\u503c\u9762");
    model.result("pg5").create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").set("number", 25);
    model.result("pg5").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "i_app", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "A", 0);
    model.study("std1").feature("stat").setIndex("pname", "i_app", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "A", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 10^range(-5,0.2,-3)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6781\u5316\u56fe");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg6").set("showlegends", false);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"cd.phis_es2"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u7535\u52bf"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg6").run();

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

    model.component("comp1").physics("tds").selection().named("sel1");
    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp1").physics("tds").create("eeic1", "ElectrodeElectrolyteInterfaceCoupling", 2);

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").physics("tds").feature("eeic1").selection().named("geom1_cyl1_bnd");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1")
         .set("iloc_src", "root.comp1.cd.es1.er1.iloc");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").set("nm", 2);
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").setIndex("Vib", -1, 0);
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c_Zn20", 0);

    model.component("comp1").variable("var1").set("c_Zn2", "c");

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
    model.study("std2").feature("cdi").setSolveFor("/physics/cd", true);
    model.study("std2").feature("cdi").setSolveFor("/physics/tds", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/cd", true);
    model.study("std2").feature("time").setSolveFor("/physics/tds", true);
    model.study("std2").feature("cdi").set("initType", "secondary");
    model.study("std2").feature("time").set("tlist", "range(0,60,3600)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("unit", new String[]{""});
    model.result("pg7").feature("glob1").set("expr", new String[]{"cd.phisext_es2"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u5916\u90e8\u7535\u4f4d"});
    model.result("pg7").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (cd)");
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 61, 0);
    model.result("pg8").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd) 1");
    model.result("pg8").create("mslc1", "Multislice");
    model.result("pg8").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg8").create("str1", "Streamline");
    model.result("pg8").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg8").feature("str1").set("posmethod", "start");
    model.result("pg8").feature("str1").set("pointtype", "arrow");
    model.result("pg8").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg8").feature("str1").set("color", "gray");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevel", 61, 0);
    model.result("pg9").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd) 1");
    model.result("pg9").create("str1", "Streamline");
    model.result("pg9").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg9").feature("str1").set("posmethod", "start");
    model.result("pg9").feature("str1").set("pointtype", "arrow");
    model.result("pg9").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg9").feature("str1").set("color", "gray");
    model.result("pg9").feature("str1").create("col1", "Color");
    model.result("pg9").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg9").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").setIndex("looplevel", 61, 0);
    model.result("pg10").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (cd) 1");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"cd.phisext"});
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").setIndex("looplevel", 61, 0);
    model.result("pg11").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd) 1");
    model.result("pg11").create("str1", "Streamline");
    model.result("pg11").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg11").feature("str1").set("posmethod", "start");
    model.result("pg11").feature("str1").set("pointtype", "arrow");
    model.result("pg11").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg11").feature("str1").set("color", "gray");
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"cd.Evsref"});
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").setIndex("looplevel", 61, 0);
    model.result("pg12").label("\u6d53\u5ea6, \u6d41\u7ebf (tds)");
    model.result("pg12").set("titletype", "custom");
    model.result("pg12").set("typeintitle", true);
    model.result("pg12").set("prefixintitle", "");
    model.result("pg12").create("str1", "Streamline");
    model.result("pg12").feature("str1").set("expr", new String[]{"tds.tflux_cx", "tds.tflux_cy", "tds.tflux_cz"});
    model.result("pg12").feature("str1").set("posmethod", "start");
    model.result("pg12").feature("str1").set("pointtype", "arrow");
    model.result("pg12").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg12").feature("str1").set("color", "gray");
    model.result("pg12").feature("str1").create("col", "Color");
    model.result("pg12").feature("str1").feature("col").set("expr", "c");
    model.result("pg12").feature("str1").feature("col").set("colortable", "Prism");
    model.result("pg12").feature("str1").feature("col").set("titletype", "custom");
    model.result("pg12").feature("str1").set("linetype", "ribbon");
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").set("data", "dset2");
    model.result("pg13").setIndex("looplevel", 61, 0);
    model.result("pg13").label("\u6d53\u5ea6, \u8868\u9762 (tds)");
    model.result("pg13").set("titletype", "custom");
    model.result("pg13").set("prefixintitle", "");
    model.result("pg13").set("expressionintitle", false);
    model.result("pg13").set("typeintitle", false);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg13").feature("surf1").set("colortable", "Prism");
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u6c60\u7535\u538b vs. \u65f6\u95f4");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").run();
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").label("\u6d53\u5ea6\u7b49\u503c\u9762");
    model.result("pg14").set("data", "dset2");
    model.result("pg14").setIndex("looplevel", 6, 0);
    model.result("pg14").create("iso1", "Isosurface");
    model.result("pg14").feature("iso1").set("expr", "c");
    model.result("pg14").feature("iso1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg14").feature("iso1").set("levelmethod", "levels");
    model.result("pg14").feature("iso1").set("levels", 0.2);
    model.result("pg14").run();

    model.component("comp1").view("view1").set("transparency", false);

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
    model.result().export("anim1").set("plotgroup", "pg14");
    model.result().export("anim1").set("looplevelinput", "manual");
    model.result().export("anim1")
         .set("looplevel", new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61});
    model.result().export("anim1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);

    model.title("\u6a59\u5b50\u7535\u6c60");

    model
         .description("\u8fd9\u4e2a\u4e09\u7ef4\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8bbe\u7f6e\u7531\u4e00\u4e2a\u6a59\u5b50\u548c\u4e24\u4e2a\u91d1\u5c5e\u9489\u5236\u6210\u7684\u7535\u6c60\uff08\u8150\u8680\u7535\u6c60\uff09\u3002\u7b2c\u4e00\u4e2a\u7814\u7a76\u6c42\u89e3\u7a33\u6001\u7535\u5316\u5b66\u7535\u6d41\u3002\u7b2c\u4e8c\u4e2a\u77ac\u6001\u7814\u7a76\u7ecf\u8fc7\u6269\u5c55\uff0c\u6a21\u62df\u91d1\u5c5e\u79bb\u5b50\u5728\u7535\u89e3\u8d28\u4e2d\u7684\u6eb6\u89e3\u548c\u4f20\u8f93\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("orange_battery.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
