/*
 * fresnel_equations.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:42 by COMSOL 6.3.0.290. */
public class fresnel_equations {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

    model.param().set("n_air", "1");
    model.param().descr("n_air", "\u6298\u5c04\u7387\uff0c\u7a7a\u6c14");
    model.param().set("n_slab", "1.5");
    model.param().descr("n_slab", "\u6298\u5c04\u7387\uff0c\u5e73\u677f");
    model.param().set("lda0", "1[m]");
    model.param().descr("lda0", "\u6ce2\u957f");
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

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.2, 0.2, 0.8});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 0.4, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").physics("ewfd").label("\u7535\u78c1\u6ce2\uff0c\u9891\u57df (ewfd\uff0cTE)");
    model.component("comp1").physics("ewfd").create("ps1", "PeriodicStructure", 3);
    model.component("comp1").physics("ewfd").feature("ps1").set("alpha1_inc", "alpha");

    model.study("std1").label("\u7814\u7a76 1 (ewfd, TE)");
    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").feature("wave").set("useparam", true);
    model.study("std1").feature("wave").setIndex("pname_aux", "n_air", 0);
    model.study("std1").feature("wave").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("wave").setIndex("punit_aux", "", 0);
    model.study("std1").feature("wave").setIndex("pname_aux", "n_air", 0);
    model.study("std1").feature("wave").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("wave").setIndex("punit_aux", "", 0);
    model.study("std1").feature("wave").setIndex("pname_aux", "alpha", 0);
    model.study("std1").feature("wave").setIndex("plistarr_aux", "range(0,2[deg],88[deg])", 0);
    model.study("std1").feature("wave").setIndex("punit_aux", "deg", 0);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0_0", "ewfd.Torder_0_0", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "alpha");
    model.result("pg2").feature("glob1").set("xdataunit", "deg");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u6781\u5316\u56fe (ewfd)");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").setIndex("looplevel", "1", 0);
    model.result("pg3").setIndex("looplevel", "1", 1);
    model.result("pg3").create("plz1", "Polarization");
    model.result("pg3").feature("plz1").set("linestyle", "solid");
    model.result("pg3").feature("plz1").set("linewidth", 2);
    model.result("pg3").feature("plz1").set("display", "0");
    model.result("pg3").feature("plz1").create("col1", "Color");
    model.result("pg3").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg3").feature("plz1").set("legend", true);
    model.result("pg3").feature("plz1").set("legendmethod", "manual");
    model.result("pg3").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg3").create("plz2", "Polarization");
    model.result("pg3").feature("plz2").set("linestyle", "dashed");
    model.result("pg3").feature("plz2").set("linewidth", 2);
    model.result("pg3").feature("plz2").set("display", "1");
    model.result("pg3").feature("plz2").create("col1", "Color");
    model.result("pg3").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("plz2").set("legend", true);
    model.result("pg3").feature("plz2").set("legendmethod", "manual");
    model.result("pg3").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u573a (ewfd, TE)");
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("expr", "ewfd.Ey");
    model.result("pg1").feature("mslc1").set("descr", "\u7535\u573a\uff0cy \u5206\u91cf");
    model.result("pg1").feature("mslc1").set("xnumber", "0");
    model.result("pg1").feature("mslc1").set("znumber", "0");
    model.result("pg1").feature("mslc1").set("colortable", "WaveLight");
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"ewfd.Poavx", "ewfd.Poavy", "ewfd.Poavz"});
    model.result("pg1").feature("arwv1").set("descr", "\u529f\u7387\u6d41\uff0c\u65f6\u95f4\u5e73\u5747");
    model.result("pg1").feature("arwv1").set("ynumber", 1);
    model.result("pg1").feature("arwv1").set("color", "green");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 36, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd\uff0cTE)");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("legendpos", "middleleft");
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
    model.result("pg3").label("\u504f\u632f\u56fe (ewfd, TE)");
    model.result("pg3").run();

    model.component("comp1").physics().copy("ewfd2", "ewfd");
    model.component("comp1").physics("ewfd2").label("\u7535\u78c1\u6ce2\uff0c\u9891\u57df (ewfd2, TM)");
    model.component("comp1").physics("ewfd2").feature("ps1").set("LinearPol", "P");

    model.study().create("std2");
    model.study("std2").label("\u7814\u7a76 2 (ewfd2, TM)");
    model.study("std2").feature().copy("wave", "std1/wave");
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u573a (ewfd2)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 45, 0);
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("expr", "ewfd2.normE");
    model.result("pg4").feature("mslc1").set("smooth", "internal");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg5").feature("glob1")
         .set("expr", new String[]{"ewfd2.Rorder_0_0", "ewfd2.Torder_0_0", "ewfd2.RTtotal", "ewfd2.Atotal"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg5").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd2)");
    model.result("pg5").feature("glob1").set("titletype", "none");
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg5").feature("glob1").set("xdataexpr", "alpha");
    model.result("pg5").feature("glob1").set("xdataunit", "deg");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("\u6781\u5316\u56fe (ewfd2)");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevelinput", "manual", 1);
    model.result("pg6").setIndex("looplevel", "1", 0);
    model.result("pg6").setIndex("looplevel", "1", 1);
    model.result("pg6").create("plz1", "Polarization");
    model.result("pg6").feature("plz1").set("linestyle", "solid");
    model.result("pg6").feature("plz1").set("linewidth", 2);
    model.result("pg6").feature("plz1").set("display", "2");
    model.result("pg6").feature("plz1").create("col1", "Color");
    model.result("pg6").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg6").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg6").feature("plz1").set("legend", true);
    model.result("pg6").feature("plz1").set("legendmethod", "manual");
    model.result("pg6").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg6").create("plz2", "Polarization");
    model.result("pg6").feature("plz2").set("linestyle", "dashed");
    model.result("pg6").feature("plz2").set("linewidth", 2);
    model.result("pg6").feature("plz2").set("display", "3");
    model.result("pg6").feature("plz2").create("col1", "Color");
    model.result("pg6").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg6").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("plz2").set("legend", true);
    model.result("pg6").feature("plz2").set("legendmethod", "manual");
    model.result("pg6").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg4").run();
    model.result("pg4").label("\u78c1\u573a (ewfd2, TM)");
    model.result("pg4").run();
    model.result("pg4").feature("mslc1").set("expr", "ewfd2.Hy");
    model.result("pg4").feature("mslc1").set("xnumber", "0");
    model.result("pg4").feature("mslc1").set("znumber", "0");
    model.result("pg4").feature("mslc1").set("colortable", "WaveLight");
    model.result("pg4").run();
    model.result("pg4").create("arwv1", "ArrowVolume");
    model.result("pg4").feature("arwv1").setIndex("expr", "ewfd2.Poavx", 0);
    model.result("pg4").feature("arwv1").setIndex("expr", "ewfd2.Poavy", 1);
    model.result("pg4").feature("arwv1").setIndex("expr", "ewfd2.Poavz", 2);
    model.result("pg4").feature("arwv1").set("ynumber", 1);
    model.result("pg4").feature("arwv1").set("color", "green");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd2\uff0cTM)");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("legendpos", "middleleft");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("linestyle", "none");
    model.result("pg5").feature("glob1").set("linemarker", "cycle");
    model.result("pg5").feature("glob1").set("markerpos", "interp");
    model.result("pg5").run();
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").feature("glob2").setIndex("expr", "abs(r_p)^2", 0);
    model.result("pg5").feature("glob2").setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u89e3\u6790", 0);
    model.result("pg5").feature("glob2").setIndex("expr", "n_slab*cos(beta)/(n_air*cos(alpha))*abs(t_p)^2", 1);
    model.result("pg5").feature("glob2").setIndex("descr", "\u900f\u5c04\u7387\uff0c\u89e3\u6790", 1);
    model.result("pg5").feature("glob2").set("legendmethod", "manual");
    model.result("pg5").feature("glob2").setIndex("legends", "Reflectance, analytic", 0);
    model.result("pg5").feature("glob2").setIndex("legends", "Transmittance, analytic", 1);
    model.result("pg5").feature("glob2").set("xdataparamunit", "\u00b0");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").label("\u504f\u632f\u56fe (ewfd2, TM)");
    model.result("pg6").run();

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

    model.study("std1").feature("wave").setSolveFor("/physics/ewbe", true);
    model.study("std2").feature("wave").setSolveFor("/physics/ewbe", true);

    model.component("comp1").physics("ewbe").label("\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc (ewbe, TE)");
    model.component("comp1").physics("ewbe").selection().set(2);
    model.component("comp1").physics("ewbe").prop("WaveVector")
         .set("k1", new String[]{"ewbe.k0*sin(alpha)", "0", "-ewbe.k0*cos(alpha)"});
    model.component("comp1").physics("ewbe").prop("WaveVector")
         .set("k2", new String[]{"ewbe.k1x", "ewbe.k1y", "-ewbe.k1z"});
    model.component("comp1").physics("ewbe").create("port1", "Port", 2);
    model.component("comp1").physics("ewbe").feature("port1").selection().set(7);
    model.component("comp1").physics("ewbe").feature("port1").set("PortType", "Periodic");
    model.component("comp1").physics("ewbe").feature("port1").set("Polarization", "LinearPol");
    model.component("comp1").physics("ewbe").feature("port1").set("alpha1_inc", "alpha");
    model.component("comp1").physics("ewbe").create("pc1", "PeriodicCondition", 2);
    model.component("comp1").physics("ewbe").feature("pc1").selection().set(4, 11);
    model.component("comp1").physics("ewbe").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewbe").feature("pc1").set("Floquet_source", "FromPeriodicPort");
    model.component("comp1").physics("ewbe").create("pc2", "PeriodicCondition", 2);
    model.component("comp1").physics("ewbe").feature("pc2").selection().set(5, 9);
    model.component("comp1").physics("ewbe").feature("pc2").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewbe").feature("pc2").set("Floquet_source", "FromPeriodicPort");
    model.component("comp1").physics("ewbe").create("imp1", "Impedance", 2);
    model.component("comp1").physics("ewbe").feature("imp1").selection().set(6);

    model.component("comp1").material().duplicate("mat3", "mat2");
    model.component("comp1").material("mat3").label("\u73bb\u7483\uff08\u8fb9\u754c\uff09");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().set(6);

    model.component("comp1").mesh("mesh1").contribute("physics/ewbe", false);
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").contribute("physics/ewfd", false);
    model.component("comp1").mesh("mesh2").contribute("physics/ewfd2", false);

    model.component("comp1").physics("ewbe").prop("MeshControl").set("elemCountT", 1);
    model.component("comp1").physics("ewbe").prop("MeshControl").set("elemCountL", 1);

    model.component("comp1").mesh("mesh2").run();

    model.study().create("std3");
    model.study("std3").label("\u7814\u7a76 3 (ewbe, TE)");
    model.study("std3").feature().copy("wave", "std2/wave");
    model.study("std3").feature("wave").setSolveFor("/physics/ewfd2", false);
    model.study("std3").feature("wave").setEntry("mesh", "geom1", "mesh2");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u573a\uff0c\u7b2c\u4e00\u4e2a\u6ce2 (ewbe)");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 45, 0);
    model.result("pg7").setIndex("looplevel", 1, 1);
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").label("\u7535\u573a");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("expr", "ewbe.normE1");
    model.result("pg7").feature("mslc1").set("smooth", "internal");
    model.result("pg7").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u7535\u573a\uff0c\u7b2c\u4e8c\u4e2a\u6ce2 (ewbe)");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").setIndex("looplevel", 45, 0);
    model.result("pg8").setIndex("looplevel", 1, 1);
    model.result("pg8").feature().create("mslc1", "Multislice");
    model.result("pg8").feature("mslc1").label("\u7535\u573a");
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("expr", "ewbe.normE2");
    model.result("pg8").feature("mslc1").set("smooth", "internal");
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("data", "parent");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("unit", new String[]{""});
    model.result("pg9").feature("glob1").set("expr", new String[]{"ewbe.Rorder_0_0"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]"});
    model.result("pg9").label("\u53cd\u5c04\u7387 (ewbe)");
    model.result("pg9").feature("glob1").set("titletype", "none");
    model.result("pg9").feature("glob1").set("xdata", "expr");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u53cd\u5c04\u7387 (1)");
    model.result("pg9").feature("glob1").set("xdataexpr", "alpha");
    model.result("pg9").feature("glob1").set("xdataunit", "deg");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").label("\u6781\u5316\u56fe (ewbe)");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg10").setIndex("looplevelinput", "manual", 0);
    model.result("pg10").setIndex("looplevelinput", "manual", 1);
    model.result("pg10").setIndex("looplevel", "1", 0);
    model.result("pg10").setIndex("looplevel", "1", 1);
    model.result("pg10").create("plz1", "Polarization");
    model.result("pg10").feature("plz1").set("linestyle", "solid");
    model.result("pg10").feature("plz1").set("linewidth", 2);
    model.result("pg10").feature("plz1").set("display", "4");
    model.result("pg10").feature("plz1").create("col1", "Color");
    model.result("pg10").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg10").feature("plz1").set("legend", true);
    model.result("pg10").feature("plz1").set("legendmethod", "manual");
    model.result("pg10").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u573a (ewbe, TE)");
    model.result("pg7").setIndex("looplevel", 36, 0);
    model.result("pg7").run();
    model.result("pg7").feature("mslc1").set("expr", "ewbe.Ey");
    model.result("pg7").feature("mslc1").set("xnumber", "0");
    model.result("pg7").feature("mslc1").set("znumber", "0");
    model.result("pg7").feature("mslc1").set("colortable", "WaveLight");
    model.result("pg7").run();
    model.result("pg7").create("arwv1", "ArrowVolume");
    model.result("pg7").feature("arwv1").set("expr", new String[]{"ewbe.Poavx", "ewbe.Poavy", "ewbe.Poavz"});
    model.result("pg7").feature("arwv1").set("descr", "\u529f\u7387\u6d41\uff0c\u65f6\u95f4\u5e73\u5747");
    model.result("pg7").feature("arwv1").set("ynumber", 1);
    model.result("pg7").feature("arwv1").set("color", "green");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result().remove("pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u53cd\u5c04\u7387 (ewbe, TE)");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("glob3", "glob1");
    model.result("pg2").run();
    model.result("pg2").feature("glob3").set("data", "dset3");
    model.result("pg2").feature("glob3").set("expr", new String[]{"ewbe.Rorder_0_0"});
    model.result("pg2").feature("glob3").set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]"});
    model.result("pg2").feature("glob3").set("unit", new String[]{"1"});
    model.result("pg2").feature("glob3").setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0] (ewbe)", 0);
    model.result("pg2").run();
    model.result("pg10").run();
    model.result("pg10").label("\u504f\u632f\u56fe (ewbe, TE)");

    model.component("comp1").physics().copy("ewbe2", "ewbe");
    model.component("comp1").physics("ewbe2").label("\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc (ewbe2, TM)");
    model.component("comp1").physics("ewbe2").feature("port1").set("LinearPol", "P");

    model.study().create("std4");
    model.study("std4").label("\u7814\u7a76 4 (ewbe2, TM)");
    model.study("std4").feature().copy("wave", "std3/wave");
    model.study("std4").feature("wave").setSolveFor("/physics/ewbe", false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").label("\u7535\u573a\uff0c\u7b2c\u4e00\u4e2a\u6ce2 (ewbe2)");
    model.result("pg11").set("data", "dset4");
    model.result("pg11").setIndex("looplevel", 45, 0);
    model.result("pg11").setIndex("looplevel", 1, 1);
    model.result("pg11").feature().create("mslc1", "Multislice");
    model.result("pg11").feature("mslc1").label("\u7535\u573a");
    model.result("pg11").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg11").feature("mslc1").set("expr", "ewbe2.normE1");
    model.result("pg11").feature("mslc1").set("smooth", "internal");
    model.result("pg11").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg11").feature("mslc1").set("data", "parent");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").label("\u7535\u573a\uff0c\u7b2c\u4e8c\u4e2a\u6ce2 (ewbe2)");
    model.result("pg12").set("data", "dset4");
    model.result("pg12").setIndex("looplevel", 45, 0);
    model.result("pg12").setIndex("looplevel", 1, 1);
    model.result("pg12").feature().create("mslc1", "Multislice");
    model.result("pg12").feature("mslc1").label("\u7535\u573a");
    model.result("pg12").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg12").feature("mslc1").set("expr", "ewbe2.normE2");
    model.result("pg12").feature("mslc1").set("smooth", "internal");
    model.result("pg12").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg12").feature("mslc1").set("data", "parent");
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").set("data", "dset4");
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("unit", new String[]{""});
    model.result("pg13").feature("glob1").set("expr", new String[]{"ewbe2.Rorder_0_0"});
    model.result("pg13").feature("glob1").set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]"});
    model.result("pg13").label("\u53cd\u5c04\u7387 (ewbe2)");
    model.result("pg13").feature("glob1").set("titletype", "none");
    model.result("pg13").feature("glob1").set("xdata", "expr");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "\u53cd\u5c04\u7387 (1)");
    model.result("pg13").feature("glob1").set("xdataexpr", "alpha");
    model.result("pg13").feature("glob1").set("xdataunit", "deg");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").label("\u6781\u5316\u56fe (ewbe2)");
    model.result("pg14").set("data", "dset4");
    model.result("pg14").set("titletype", "manual");
    model.result("pg14").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg14").setIndex("looplevelinput", "manual", 0);
    model.result("pg14").setIndex("looplevelinput", "manual", 1);
    model.result("pg14").setIndex("looplevel", "1", 0);
    model.result("pg14").setIndex("looplevel", "1", 1);
    model.result("pg14").create("plz1", "Polarization");
    model.result("pg14").feature("plz1").set("linestyle", "solid");
    model.result("pg14").feature("plz1").set("linewidth", 2);
    model.result("pg14").feature("plz1").set("display", "5");
    model.result("pg14").feature("plz1").create("col1", "Color");
    model.result("pg14").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg14").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg14").feature("plz1").set("legend", true);
    model.result("pg14").feature("plz1").set("legendmethod", "manual");
    model.result("pg14").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg11").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg11").label("\u78c1\u573a (ewbe2, TM)");
    model.result("pg11").setIndex("looplevel", 36, 0);
    model.result("pg11").run();
    model.result("pg11").feature("mslc1").set("expr", "ewbe2.Hy");
    model.result("pg11").feature("mslc1").set("xnumber", "0");
    model.result("pg11").feature("mslc1").set("znumber", "0");
    model.result("pg11").feature("mslc1").set("colortable", "WaveLight");
    model.result("pg11").run();
    model.result("pg11").create("arwv1", "ArrowVolume");
    model.result("pg11").feature("arwv1").set("expr", new String[]{"ewbe2.Poavx", "ewbe2.Poavy", "ewbe2.Poavz"});
    model.result("pg11").feature("arwv1").set("descr", "\u529f\u7387\u6d41\uff0c\u65f6\u95f4\u5e73\u5747");
    model.result("pg11").feature("arwv1").set("ynumber", 1);
    model.result("pg11").feature("arwv1").set("color", "green");
    model.result("pg11").run();
    model.result("pg12").run();
    model.result().remove("pg12");
    model.result("pg13").run();
    model.result("pg13").label("\u53cd\u5c04\u7387 (ewbe2, TM)");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("glob3", "glob1");
    model.result("pg5").run();
    model.result("pg5").feature("glob3").set("data", "dset4");
    model.result("pg5").feature("glob3").set("expr", new String[]{"ewbe2.Rorder_0_0"});
    model.result("pg5").feature("glob3").set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]"});
    model.result("pg5").feature("glob3").set("unit", new String[]{"1"});
    model.result("pg5").feature("glob3").setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0] (ewbe2)", 0);
    model.result("pg5").run();
    model.result("pg14").run();
    model.result("pg14").label("\u504f\u632f\u56fe (ewbe2\uff0cTM)");

    model.component("comp1").mesh("mesh1").contribute("physics/ewbe2", false);

    model.study("std1").feature("wave").setSolveFor("/physics/ewfd2", false);
    model.study("std1").feature("wave").setSolveFor("/physics/ewbe", false);
    model.study("std1").feature("wave").setSolveFor("/physics/ewbe2", false);
    model.study("std2").feature("wave").setSolveFor("/physics/ewbe", false);
    model.study("std2").feature("wave").setSolveFor("/physics/ewbe2", false);
    model.study("std3").feature("wave").setSolveFor("/physics/ewbe2", false);

    model.result("pg4").run();

    model.title("\u83f2\u6d85\u5c14\u65b9\u7a0b");

    model
         .description("\u901a\u8fc7\u81ea\u7531\u7a7a\u95f4\u4f20\u64ad\u7684\u5e73\u9762\u7535\u78c1\u6ce2\u4ee5\u4e00\u5b9a\u7684\u89d2\u5ea6\u5165\u5c04\u5230\u65e0\u9650\u5927\u7535\u4ecb\u8d28\u4e0a\u3002\u672c\u4f8b\u8ba1\u7b97\u53cd\u5c04\u7cfb\u6570\u548c\u900f\u5c04\u7cfb\u6570\uff0c\u5e76\u5c06\u8ba1\u7b97\u7ed3\u679c\u4e0e\u83f2\u6d85\u5c14\u65b9\u7a0b\u7ed9\u51fa\u7684\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("fresnel_equations.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
