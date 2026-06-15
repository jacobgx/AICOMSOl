/*
 * fresnel_equations.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:44 by COMSOL 6.3.0.290. */
public class fresnel_equations {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);

    model.param().set("n_air", "1");
    model.param().descr("n_air", "\u6298\u5c04\u7387\uff0c\u7a7a\u6c14");
    model.param().set("n_slab", "1.5");
    model.param().descr("n_slab", "\u6298\u5c04\u7387\uff0c\u5e73\u677f");
    model.param().set("lda0", "1[m]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("alpha", "70[deg]");
    model.param().descr("alpha", "\u5165\u5c04\u89d2");
    model.param().set("beta", "asin(n_air*sin(alpha)/n_slab)");
    model.param().descr("beta", "\u6298\u5c04\u89d2");
    model.param().set("alpha_brewster", "atan(n_slab/n_air)");
    model.param().descr("alpha_brewster", "Brewster \u89d2\uff0c\u4ec5 TM");
    model.param().set("r_s", "(n_air*cos(alpha)-n_slab*cos(beta))/(n_air*cos(alpha)+n_slab*cos(beta))");
    model.param().descr("r_s", "\u53cd\u5c04\u7cfb\u6570\uff0cTE");
    model.param().set("r_p", "(n_slab*cos(alpha)-n_air*cos(beta))/(n_air*cos(beta)+n_slab*cos(alpha))");
    model.param().descr("r_p", "\u53cd\u5c04\u7cfb\u6570\uff0cTM");
    model.param().set("t_s", "(2*n_air*cos(alpha))/(n_air*cos(alpha)+n_slab*cos(beta))");
    model.param().descr("t_s", "\u900f\u5c04\u7cfb\u6570\uff0cTE");
    model.param().set("t_p", "(2*n_air*cos(alpha))/(n_air*cos(beta)+n_slab*cos(alpha))");
    model.param().descr("t_p", "\u900f\u5c04\u7cfb\u6570\uff0cTM");

    model.study("std1").label("\u7814\u7a76 1 (TE)");
    model.study("std1").feature("freq").set("plist", "f0");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.2, 0.2, 0.8});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 0.4, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("emw").feature("wee1").set("DisplacementFieldModel", "RefractiveIndex");
    model.component("comp1").physics("emw").label("\u7535\u78c1\u6ce2\uff0c\u9891\u57df (TE)");
    model.component("comp1").physics("emw").create("port1", "Port", 2);
    model.component("comp1").physics("emw").feature("port1").selection().set(7);
    model.component("comp1").physics("emw").feature("port1").set("PortType", "Periodic");
    model.component("comp1").physics("emw").feature("port1").set("Polarization", "LinearPol");
    model.component("comp1").physics("emw").feature("port1").set("alpha1_inc", "alpha");
    model.component("comp1").physics("emw").create("port2", "Port", 2);
    model.component("comp1").physics("emw").feature("port2").selection().set(3);
    model.component("comp1").physics("emw").feature("port2").set("PortType", "Periodic");
    model.component("comp1").physics("emw").feature("port2").set("Polarization", "LinearPol");
    model.component("comp1").physics("emw").create("pc1", "PeriodicCondition", 2);
    model.component("comp1").physics("emw").feature("pc1").selection().set(1, 4, 10, 11);
    model.component("comp1").physics("emw").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("emw").feature("pc1").set("Floquet_source", "FromPeriodicPort");
    model.component("comp1").physics("emw").create("pc2", "PeriodicCondition", 2);
    model.component("comp1").physics("emw").feature("pc2").selection().set(2, 5, 8, 9);
    model.component("comp1").physics("emw").feature("pc2").set("PeriodicType", "Floquet");
    model.component("comp1").physics("emw").feature("pc2").set("Floquet_source", "FromPeriodicPort");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_air"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u73bb\u7483");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_slab"});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "n_air", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "n_air", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "alpha", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,2[deg],88[deg])", 0);
    model.study("std1").feature("param").setIndex("punit", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"emw.S11dB", "emw.S21dB"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg2").label("S \u53c2\u6570 (emw)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg2").feature("glob1").set("xdataexpr", "alpha");
    model.result("pg2").feature("glob1").set("xdataunit", "deg");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "SmithGroup");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rgr1", "ReflectionGraph");
    model.result("pg3").feature("rgr1").set("unit", new String[]{""});
    model.result("pg3").feature("rgr1").set("expr", new String[]{"emw.S11"});
    model.result("pg3").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg3").label("\u53f2\u5bc6\u65af\u56fe (emw)");
    model.result("pg3").feature("rgr1").set("titletype", "manual");
    model.result("pg3").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg3").feature("rgr1").set("linemarker", "point");
    model.result("pg3").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("rgr1").create("col1", "Color");
    model.result("pg3").feature("rgr1").feature("col1").set("expr", "emw.freq/1e9");
    model.result("pg3").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u573a, \u5bf9\u6570 (emw)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw.normE");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().set(1, 2, 4, 5, 8, 9, 10, 11);
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.7);
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "emw.normE");
    model.result("pg4").feature("surf2").create("sel1", "Selection");
    model.result("pg4").feature("surf2").feature("sel1").selection().set(6);
    model.result("pg4").feature("surf2").set("colortable", "Dipole");
    model.result("pg4").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf2").create("tran1", "Transparency");
    model.result("pg4").feature("surf2").feature("tran1").set("transparency", 0.3);
    model.result("pg4").create("surf3", "Surface");
    model.result("pg4").feature("surf3").set("expr", "emw.normE");
    model.result("pg4").feature("surf3").create("sel1", "Selection");
    model.result("pg4").feature("surf3").feature("sel1").selection().set(3, 7);
    model.result("pg4").feature("surf3").set("colortable", "Dipole");
    model.result("pg4").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf3").create("tran1", "Transparency");
    model.result("pg4").feature("surf3").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").camera()
         .set("position", new double[]{-0.9530267508133599, -1.2851951433264692, 1.170418055161186});
    model.component("comp1").view("view2").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view2").camera().set("zoomanglefull", 44.075565338134766);

    model.result("pg4").set("view", "view2");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u6781\u5316\u56fe (emw)");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", "1", 0);
    model.result("pg5").create("plz1", "Polarization");
    model.result("pg5").feature("plz1").set("linestyle", "solid");
    model.result("pg5").feature("plz1").set("linewidth", 2);
    model.result("pg5").feature("plz1").set("display", "0");
    model.result("pg5").feature("plz1").create("col1", "Color");
    model.result("pg5").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg5").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg5").feature("plz1").set("legend", true);
    model.result("pg5").feature("plz1").set("legendmethod", "manual");
    model.result("pg5").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg5").create("plz2", "Polarization");
    model.result("pg5").feature("plz2").set("linestyle", "dashed");
    model.result("pg5").feature("plz2").set("linewidth", 2);
    model.result("pg5").feature("plz2").set("display", "1");
    model.result("pg5").feature("plz2").create("col1", "Color");
    model.result("pg5").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg5").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("plz2").set("legend", true);
    model.result("pg5").feature("plz2").set("legendmethod", "manual");
    model.result("pg5").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u573a (emw, TE)");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("expr", "emw.Ey");
    model.result("pg1").feature("mslc1").set("descr", "\u7535\u573a\uff0cy \u5206\u91cf");
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("znumber", "0");
    model.result("pg1").feature("mslc1").set("colortable", "WaveLight");
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"emw.Poavx", "emw.Poavy", "emw.Poavz"});
    model.result("pg1").feature("arwv1").set("descr", "\u529f\u7387\u6d41\uff0c\u65f6\u95f4\u5e73\u5747");
    model.result("pg1").feature("arwv1").set("ynumber", 1);
    model.result("pg1").feature("arwv1").set("color", "green");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 36, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("S \u53c2\u6570 (emw, TE)");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabel", "S \u53c2\u6570");
    model.result("pg2").set("legendpos", "middleleft");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").setIndex("expr", "abs(emw.S11)^2", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u53cd\u5c04\u7387", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "abs(emw.S21)^2", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "\u900f\u5c04\u7387", 1);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linestyle", "none");
    model.result("pg2").feature("glob1").set("linemarker", "cycle");
    model.result("pg2").feature("glob1").set("markerpos", "interp");
    model.result("pg2").run();
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").setIndex("expr", "abs(r_s)^2", 0);
    model.result("pg2").feature("glob2").setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u89e3\u6790", 0);
    model.result("pg2").feature("glob2").setIndex("expr", "n_slab*cos(beta)/(n_air*cos(alpha))*abs(t_s)^2", 1);
    model.result("pg2").feature("glob2").setIndex("descr", "\u900f\u5c04\u7387\uff0c\u89e3\u6790", 1);
    model.result("pg2").feature("glob2").set("xdataparamunit", "\u00b0");
    model.result("pg2").feature("glob2").set("legendmethod", "manual");
    model.result("pg2").feature("glob2").setIndex("legends", "Reflectance, analytic", 0);
    model.result("pg2").feature("glob2").setIndex("legends", "Transmittance, analytic", 1);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").label("\u53f2\u5bc6\u65af\u56fe (emw, TE)");
    model.result("pg3").run();
    model.result("pg3").feature("rgr1").feature("col1").set("expr", "alpha");
    model.result("pg3").feature("rgr1").feature("col1").set("unit", "\u00b0");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3")
         .set("title", "\u53cd\u5c04\u56fe\uff1aS \u53c2\u6570\uff1b\u989c\u8272\uff1a\u5165\u5c04\u89d2\uff08\u5ea6\uff09");
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg5").label("\u504f\u632f\u56fe (emw, TE)");
    model.result("pg5").run();

    model.component("comp1").physics().copy("emw2", "emw");
    model.component("comp1").physics("emw2").label("\u7535\u78c1\u6ce2\uff0c\u9891\u57df (TM)");

    model.study("std1").feature("freq").setSolveFor("/physics/emw2", false);

    model.component("comp1").physics("emw2").feature("port1").set("LinearPol", "P");
    model.component("comp1").physics("emw2").feature("port2").set("LinearPol", "P");

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std2").feature("freq").setSolveFor("/physics/emw2", true);
    model.study("std2").feature("freq").set("plist", "f0");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", false);
    model.study("std2").label("\u7814\u7a76 2 (TM)");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "n_air", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "n_air", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "alpha", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(0,2[deg],88[deg])", 0);
    model.study("std2").feature("param").setIndex("punit", "deg", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u7535\u573a (emw2)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 45, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg6").feature("mslc1").set("expr", "emw2.normE");
    model.result("pg6").feature("mslc1").set("smooth", "internal");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg6").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg6").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg7").feature("glob1").set("expr", new String[]{"emw2.S11dB", "emw2.S21dB"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"S11", "S21"});
    model.result("pg7").label("S \u53c2\u6570 (emw2)");
    model.result("pg7").feature("glob1").set("titletype", "none");
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "S \u53c2\u6570 (dB)");
    model.result("pg7").feature("glob1").set("xdataexpr", "alpha");
    model.result("pg7").feature("glob1").set("xdataunit", "deg");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg8", "SmithGroup");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("rgr1", "ReflectionGraph");
    model.result("pg8").feature("rgr1").set("unit", new String[]{""});
    model.result("pg8").feature("rgr1").set("expr", new String[]{"emw2.S11"});
    model.result("pg8").feature("rgr1").set("descr", new String[]{"S11"});
    model.result("pg8").label("\u53f2\u5bc6\u65af\u56fe (emw2)");
    model.result("pg8").feature("rgr1").set("titletype", "manual");
    model.result("pg8").feature("rgr1")
         .set("title", "\u53cd\u5c04\u56fe: S \u53c2\u6570, \u989c\u8272: \u9891\u7387 (GHz)");
    model.result("pg8").feature("rgr1").set("linemarker", "point");
    model.result("pg8").feature("rgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("rgr1").create("col1", "Color");
    model.result("pg8").feature("rgr1").feature("col1").set("expr", "emw2.freq/1e9");
    model.result("pg8").feature("rgr1").feature("col1").set("colortable", "Spectrum");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").label("\u7535\u573a, \u5bf9\u6570 (emw2)");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", "emw2.normE");
    model.result("pg9").feature("surf1").create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().set(1, 2, 4, 5, 8, 9, 10, 11);
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf1").create("tran1", "Transparency");
    model.result("pg9").feature("surf1").feature("tran1").set("transparency", 0.7);
    model.result("pg9").create("surf2", "Surface");
    model.result("pg9").feature("surf2").set("expr", "emw2.normE");
    model.result("pg9").feature("surf2").create("sel1", "Selection");
    model.result("pg9").feature("surf2").feature("sel1").selection().set(6);
    model.result("pg9").feature("surf2").set("colortable", "Dipole");
    model.result("pg9").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf2").create("tran1", "Transparency");
    model.result("pg9").feature("surf2").feature("tran1").set("transparency", 0.3);
    model.result("pg9").create("surf3", "Surface");
    model.result("pg9").feature("surf3").set("expr", "emw2.normE");
    model.result("pg9").feature("surf3").create("sel1", "Selection");
    model.result("pg9").feature("surf3").feature("sel1").selection().set(3, 7);
    model.result("pg9").feature("surf3").set("colortable", "Dipole");
    model.result("pg9").feature("surf3").set("colorscalemode", "logarithmic");
    model.result("pg9").feature("surf3").create("tran1", "Transparency");
    model.result("pg9").feature("surf3").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view4", "geom1");
    model.component("comp1").view("view4").camera()
         .set("position", new double[]{-0.9530267508133599, -1.285195246986721, 1.170418055161186});
    model.component("comp1").view("view4").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view4").camera().set("zoomanglefull", 44.72673416137695);

    model.result("pg9").set("view", "view4");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").label("\u6781\u5316\u56fe (emw2)");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg10").setIndex("looplevelinput", "manual", 0);
    model.result("pg10").setIndex("looplevel", "1", 0);
    model.result("pg10").create("plz1", "Polarization");
    model.result("pg10").feature("plz1").set("linestyle", "solid");
    model.result("pg10").feature("plz1").set("linewidth", 2);
    model.result("pg10").feature("plz1").set("display", "2");
    model.result("pg10").feature("plz1").create("col1", "Color");
    model.result("pg10").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg10").feature("plz1").set("legend", true);
    model.result("pg10").feature("plz1").set("legendmethod", "manual");
    model.result("pg10").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg10").create("plz2", "Polarization");
    model.result("pg10").feature("plz2").set("linestyle", "dashed");
    model.result("pg10").feature("plz2").set("linewidth", 2);
    model.result("pg10").feature("plz2").set("display", "3");
    model.result("pg10").feature("plz2").create("col1", "Color");
    model.result("pg10").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg10").feature("plz2").set("legend", true);
    model.result("pg10").feature("plz2").set("legendmethod", "manual");
    model.result("pg10").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg6").run();
    model.result("pg6").label("\u78c1\u573a (emw2, TM)");
    model.result("pg6").run();
    model.result("pg6").feature("mslc1").set("expr", "emw2.Hy");
    model.result("pg6").feature("mslc1").set("xnumber", "0");
    model.result("pg6").feature("mslc1").set("znumber", "0");
    model.result("pg6").feature("mslc1").set("colortable", "WaveLight");
    model.result("pg6").run();
    model.result("pg6").create("arwv1", "ArrowVolume");
    model.result("pg6").feature("arwv1").setIndex("expr", "emw2.Poavx", 0);
    model.result("pg6").feature("arwv1").setIndex("expr", "emw2.Poavy", 1);
    model.result("pg6").feature("arwv1").setIndex("expr", "emw2.Poavz", 2);
    model.result("pg6").feature("arwv1").set("ynumber", 1);
    model.result("pg6").feature("arwv1").set("color", "green");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").label("S \u53c2\u6570 (emw, TM)");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("ylabel", "S \u53c2\u6570");
    model.result("pg7").set("legendpos", "middleleft");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("expr", "abs(emw2.S11)^2", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u53cd\u5c04\u7387", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "abs(emw2.S21)^2", 1);
    model.result("pg7").feature("glob1").setIndex("descr", "\u900f\u5c04\u7387", 1);
    model.result("pg7").feature("glob1").set("linestyle", "none");
    model.result("pg7").feature("glob1").set("linemarker", "cycle");
    model.result("pg7").feature("glob1").set("markerpos", "interp");
    model.result("pg7").run();
    model.result("pg7").create("glob2", "Global");
    model.result("pg7").feature("glob2").set("markerpos", "datapoints");
    model.result("pg7").feature("glob2").set("linewidth", "preference");
    model.result("pg7").feature("glob2").setIndex("expr", "abs(r_p)^2", 0);
    model.result("pg7").feature("glob2").setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u89e3\u6790", 0);
    model.result("pg7").feature("glob2").setIndex("expr", "n_slab*cos(beta)/(n_air*cos(alpha))*abs(t_p)^2", 1);
    model.result("pg7").feature("glob2").setIndex("descr", "\u900f\u5c04\u7387\uff0c\u89e3\u6790", 1);
    model.result("pg7").feature("glob2").set("legendmethod", "manual");
    model.result("pg7").feature("glob2").setIndex("legends", "Reflectance, analytic", 0);
    model.result("pg7").feature("glob2").setIndex("legends", "Transmittance, analytic", 1);
    model.result("pg7").feature("glob2").set("xdataparamunit", "\u00b0");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").label("\u53f2\u5bc6\u65af\u56fe (emw2, TM)");
    model.result("pg8").run();
    model.result("pg8").feature("rgr1").feature("col1").set("expr", "alpha");
    model.result("pg8").feature("rgr1").feature("col1").set("unit", "\u00b0");
    model.result("pg8").run();
    model.result("pg8").set("titletype", "manual");
    model.result("pg8")
         .set("title", "\u53cd\u5c04\u56fe\uff1aS \u53c2\u6570\uff1b\u989c\u8272\uff1a\u5165\u5c04\u89d2\uff08\u5ea6\uff09");
    model.result("pg8").run();
    model.result("pg10").run();
    model.result("pg10").label("\u504f\u632f\u56fe (emw2, TM)");
    model.result("pg10").run();
    model.result("pg1").run();

    model.title("\u83f2\u6d85\u5c14\u65b9\u7a0b");

    model
         .description("\u901a\u8fc7\u81ea\u7531\u7a7a\u95f4\u4f20\u64ad\u7684\u5e73\u9762\u7535\u78c1\u6ce2\u4ee5\u4e00\u5b9a\u7684\u89d2\u5ea6\u5165\u5c04\u5230\u65e0\u9650\u5927\u7535\u4ecb\u8d28\u4e0a\u3002\u672c\u4f8b\u8ba1\u7b97\u53cd\u5c04\u7cfb\u6570\u548c\u900f\u5c04\u7cfb\u6570\uff0c\u5e76\u5c06\u8ba1\u7b97\u7ed3\u679c\u4e0e\u83f2\u6d85\u5c14\u65b9\u7a0b\u7ed9\u51fa\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("fresnel_equations.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
