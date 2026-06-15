/*
 * open_pipe.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class open_pipe {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("acpr2", "PressureAcoustics", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a", "0.25[m]", "\u7ba1\u534a\u5f84");
    model.param().set("L", "1.5[m]", "\u7ba1\u957f");
    model.param().set("rho_air", "1.25[kg/m^3]", "\u7a7a\u6c14\u5bc6\u5ea6");
    model.param().set("c_air", "343[m/s]", "\u7a7a\u6c14\u4e2d\u7684\u58f0\u901f");
    model.param().set("a0", "1[m/s^2]", "\u5916\u52a0\u6d3b\u585e\u52a0\u901f\u5ea6");
    model.param().set("fmin", "10[Hz]", "\u6700\u5c0f\u7814\u7a76\u9891\u7387");
    model.param().set("fmax", "1000[Hz]", "\u6700\u5927\u7814\u7a76\u9891\u7387");
    model.param()
         .set("hmax", "c_air/fmax/6", "\u7528\u4e8e\u89e3\u6790\u6700\u5c0f\u6ce2\u957f\u7684\u7f51\u683c\u5927\u5c0f");
    model.param().set("alpha11", "3.832", "\u8d1d\u585e\u5c14\u51fd\u6570\u4e00\u9636\u5bfc\u6570\u4e3a\u96f6");
    model.param().set("fcut", "(alpha11*c_air/a)/(2*pi)", "\u622a\u6b62\u9891\u7387");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"a", "L"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-L"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "6*a");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "2*a", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_air"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"c_air"});

    model.component("comp1").label("\u7ec4\u4ef6 1 - \u6709\u6cd5\u5170\u7ba1\u7684\u58f0\u8f90\u5c04");

    model.component("comp1").physics("acpr")
         .label("\u538b\u529b\u58f0\u5b66\uff0c\u9891\u57df - \u963b\u6297\u6a21\u578b");
    model.component("comp1").physics("acpr").selection().set(1);
    model.component("comp1").physics("acpr").create("nacc1", "NormalAcceleration", 1);
    model.component("comp1").physics("acpr").feature("nacc1").selection().set(2);
    model.component("comp1").physics("acpr").feature("nacc1").set("nacc", "a0");
    model.component("comp1").physics("acpr").create("imp1", "Impedance", 1);
    model.component("comp1").physics("acpr").feature("imp1").selection().set(4);
    model.component("comp1").physics("acpr").feature("imp1").set("ImpedanceModel", "EndImpedance");
    model.component("comp1").physics("acpr").feature("imp1").set("innera", "a");
    model.component("comp1").physics("acpr2")
         .label("\u538b\u529b\u58f0\u5b66\uff0c\u9891\u57df 2 - \u5b8c\u7f8e\u5339\u914d\u5c42");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(3);
    model.component("comp1").coordSystem("pml1").set("stretchingType", "rational");
    model.component("comp1").coordSystem("pml1").set("wavelengthSource", "acpr2");
    model.component("comp1").coordSystem("pml1").set("PMLfactor", "0.5");
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "5");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(4);

    model.component("comp1").physics("acpr2").create("nacc1", "NormalAcceleration", 1);
    model.component("comp1").physics("acpr2").feature("nacc1").selection().set(2);
    model.component("comp1").physics("acpr2").feature("nacc1").set("nacc", "a0");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "hmax");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 6.0E-4);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "hmax/15");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "min(hmax,a/4)");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr2", true);
    model.study("std1")
         .label("\u7814\u7a76 1 - \u4f7f\u7528\u963b\u6297\u8fb9\u754c\u6761\u4ef6\u7684\u6709\u6cd5\u5170\u7ba1");
    model.study("std1").feature("freq")
         .set("plist", "{10, 10.3, 10.6, 10.9, 11.2, 11.5, 11.8, 12.2, 12.5, 12.8, 13.2, 13.6, 14, 14.5, 15, 15.5, 16, 16.5, 17, 17.5, 18, 18.5, 19, 19.5, 20, 20.6, 21.2, 21.8, 22.4, 23, 23.6, 24.3, 25, 25.8, 26.5, 27.2, 28, 29, 30, 30.7, 31.5, 32.5, 33.5, 34.5, 35.5, 36.5, 37.5, 38.7, 40, 41.2, 42.5, 43.7, 45, 46.2, 47.5, 48.7, 50, 51.5, 53, 54.5, 56, 58, 60, 61.5, 63, 65, 67, 69, 71, 73, 75, 77.5, 80, 82.5, 85, 87.5, 90, 92.5, 95, 97.5, 100, 103, 106, 109, 112, 115, 118, 122, 125, 128, 132, 136, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 206, 212, 218, 224, 230, 236, 243, 250, 258, 265, 272, 280, 290, 300, 307, 315, 325, 335, 345, 355, 365, 375, 387, 400, 412, 425, 437, 450, 462, 475, 487, 500, 515, 530, 545, 560, 580, 600, 615, 630, 650, 670, 690, 710, 730, 750, 775, 800, 825, 850, 875, 900, 925, 950, 975, 1e3}");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std2")
         .label("\u7814\u7a76 2 - \u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684\u6709\u6cd5\u5170\u7ba1");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq")
         .set("plist", "{10, 10.3, 10.6, 10.9, 11.2, 11.5, 11.8, 12.2, 12.5, 12.8, 13.2, 13.6, 14, 14.5, 15, 15.5, 16, 16.5, 17, 17.5, 18, 18.5, 19, 19.5, 20, 20.6, 21.2, 21.8, 22.4, 23, 23.6, 24.3, 25, 25.8, 26.5, 27.2, 28, 29, 30, 30.7, 31.5, 32.5, 33.5, 34.5, 35.5, 36.5, 37.5, 38.7, 40, 41.2, 42.5, 43.7, 45, 46.2, 47.5, 48.7, 50, 51.5, 53, 54.5, 56, 58, 60, 61.5, 63, 65, 67, 69, 71, 73, 75, 77.5, 80, 82.5, 85, 87.5, 90, 92.5, 95, 97.5, 100, 103, 106, 109, 112, 115, 118, 122, 125, 128, 132, 136, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 206, 212, 218, 224, 230, 236, 243, 250, 258, 265, 272, 280, 290, 300, 307, 315, 325, 335, 345, 355, 365, 375, 387, 400, 412, 425, 437, 450, 462, 475, 487, 500, 515, 530, 545, 560, 580, 600, 615, 630, 650, 670, 690, 710, 730, 750, 775, 800, 825, 850, 875, 900, 925, 950, 975, 1e3}");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 161, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 161, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "rev1");
    model.result("pg3").setIndex("looplevel", 161, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b, 3D (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 161, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\u7ea7, 3D (acpr)");
    model.result("pg1").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6709\u6cd5\u5170\u7ba1\uff1aZ_rad vs. f");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "f [Hz]");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "Z_rad [Pa*s/m]");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").set("data", "dset1");
    model.result("pg5").feature("ptgr1").selection().set(2);
    model.result("pg5").feature("ptgr1").set("expr", "real(acpr.imp1.Zn)");
    model.result("pg5").feature("ptgr1").set("linecolor", "blue");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u963b\u6297\u6a21\u578b real(Z)", 0);
    model.result("pg5").run();
    model.result("pg5").create("ptgr2", "PointGraph");
    model.result("pg5").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr2").set("linewidth", "preference");
    model.result("pg5").feature("ptgr2").set("data", "dset1");
    model.result("pg5").feature("ptgr2").selection().set(2);
    model.result("pg5").feature("ptgr2").set("expr", "imag(acpr.imp1.Zn)");
    model.result("pg5").feature("ptgr2").set("linecolor", "red");
    model.result("pg5").feature("ptgr2").set("legend", true);
    model.result("pg5").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr2").setIndex("legends", "\u963b\u6297\u6a21\u578b imag(Z)", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("data", "dset2");
    model.result("pg5").feature("glob1").setIndex("expr", "intop1(p2)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "", 0);
    model.result("pg5").feature("glob1").set("linestyle", "dashed");
    model.result("pg5").feature("glob1").set("linecolor", "blue");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").feature("glob2").set("data", "dset2");
    model.result("pg5").feature("glob2").setIndex("expr", "imag(intop1(p2)/intop1(acpr2.vz))", 0);
    model.result("pg5").feature("glob2")
         .setIndex("descr", "\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684 imag(Z)", 0);
    model.result("pg5").feature("glob2").set("linestyle", "dashed");
    model.result("pg5").feature("glob2").set("linecolor", "red");
    model.result("pg5").run();
    model.result("pg5").set("xlog", true);
    model.result("pg5").set("ylog", true);
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "intop1(p2)/intop1(acpr2.vz)", 0);
    model.result("pg5").feature("glob1")
         .setIndex("descr", "\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684 real(Z)", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u6709\u6cd5\u5170\u7ba1\uff1aZ_rad vs. k*a");
    model.result("pg6").set("xlabel", "k*a [-]");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("xdata", "expr");
    model.result("pg6").feature("ptgr1").set("xdataexpr", "acpr.omega/c_air*a");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").set("xdata", "expr");
    model.result("pg6").feature("ptgr2").set("xdataexpr", "acpr.omega/c_air*a");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "acpr.omega/c_air*a");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").set("xdata", "expr");
    model.result("pg6").feature("glob2").set("xdataexpr", "acpr.omega/c_air*a");
    model.result("pg6").set("xlog", false);
    model.result("pg6").set("ylog", false);
    model.result("pg6").run();
    model.result("pg6").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);
    model.component("comp2").geom("geom2").axisymmetric(true);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").label("\u7ec4\u4ef6 2 - \u65e0\u6cd5\u5170\u7ba1\u7684\u58f0\u8f90\u5c04");

    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").set("size", new String[]{"a", "L"});
    model.component("comp2").geom("geom2").feature("r1").set("pos", new String[]{"0", "-L"});
    model.component("comp2").geom("geom2").run("r1");
    model.component("comp2").geom("geom2").create("c1", "Circle");
    model.component("comp2").geom("geom2").feature("c1").set("r", "0.9*L");
    model.component("comp2").geom("geom2").feature("c1").set("angle", 180);
    model.component("comp2").geom("geom2").feature("c1").set("rot", -90);
    model.component("comp2").geom("geom2").feature("c1").setIndex("layer", "0.1*L", 0);
    model.component("comp2").geom("geom2").runPre("fin");
    model.component("comp2").geom("geom2").create("uni1", "Union");
    model.component("comp2").geom("geom2").feature("uni1").selection("input").set("c1", "r1");
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").create("cmd1", "CompositeDomains");
    model.component("comp2").geom("geom2").feature("cmd1").selection("input").set("fin", 1, 2, 3);
    model.component("comp2").geom("geom2").run("cmd1");

    model.component("comp2").physics().create("acpr3", "PressureAcoustics", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/acpr3", true);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr3", true);

    model.component("comp2").physics("acpr3")
         .label("\u538b\u529b\u58f0\u5b66\uff0c\u9891\u57df 3 - \u963b\u6297\u8fb9\u754c\u6761\u4ef6");
    model.component("comp2").physics("acpr3").selection().set(1);
    model.component("comp2").physics("acpr3").create("nacc1", "NormalAcceleration", 1);
    model.component("comp2").physics("acpr3").feature("nacc1").selection().set(2);
    model.component("comp2").physics("acpr3").feature("nacc1").set("nacc", "a0");
    model.component("comp2").physics("acpr3").create("imp1", "Impedance", 1);
    model.component("comp2").physics("acpr3").feature("imp1").selection().set(4);
    model.component("comp2").physics("acpr3").feature("imp1").set("ImpedanceModel", "EndImpedance");
    model.component("comp2").physics("acpr3").feature("imp1").set("EndImpedanceType", "UnflangedPipeCircular");
    model.component("comp2").physics("acpr3").feature("imp1").set("innera", "a");

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup("def").set("density", new String[]{"rho_air"});
    model.component("comp2").material("mat2").propertyGroup("def").set("soundspeed", new String[]{"c_air"});

    model.component("comp2").physics().create("acpr4", "PressureAcoustics", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/acpr4", true);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr4", true);

    model.component("comp2").physics("acpr4")
         .label("\u538b\u529b\u58f0\u5b66\uff0c\u9891\u57df 4 - \u5b8c\u7f8e\u5339\u914d\u5c42");
    model.component("comp2").physics("acpr4").create("nacc1", "NormalAcceleration", 1);
    model.component("comp2").physics("acpr4").feature("nacc1").selection().set(2);
    model.component("comp2").physics("acpr4").feature("nacc1").set("nacc", "a0");
    model.component("comp2").physics("acpr4").create("ishb1", "InteriorSoundHard", 1);
    model.component("comp2").physics("acpr4").feature("ishb1").selection().set(7, 8);

    model.component("comp2").coordSystem().create("pml2", "PML");
    model.component("comp2").coordSystem("pml2").selection().set(3, 4);
    model.component("comp2").coordSystem("pml2").set("wavelengthSource", "acpr4");
    model.component("comp2").coordSystem("pml2").set("PMLfactor", "0.5");
    model.component("comp2").coordSystem("pml2").set("PMLgamma", "5");
    model.component("comp2").coordSystem("pml2").set("stretchingType", "rational");

    model.component("comp2").cpl().create("intop2", "Integration");
    model.component("comp2").cpl("intop2").set("axisym", true);
    model.component("comp2").cpl("intop2").selection().geom("geom2", 1);
    model.component("comp2").cpl("intop2").selection().set(4);

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().geom("geom2", 2);
    model.component("comp2").mesh("mesh2").feature("map1").selection().set(3, 4);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().set(5, 7, 9);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("numelem", 8);
    model.component("comp2").mesh("mesh2").run("map1");
    model.component("comp2").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "hmax");
    model.component("comp2").mesh("mesh2").feature("size").set("hmin", 6.0E-4);
    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection().geom("geom2", 0);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection().set(8);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmax", "hmax/15");
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().geom("geom2", 2);
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().set(1, 2);
    model.component("comp2").mesh("mesh2").feature("ftri1").create("size2", "Size");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size2").selection().geom("geom2", 1);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size2").selection().set(4);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size2").set("hmax", "min(hmax,a/4)");
    model.component("comp2").mesh("mesh2").run("ftri1");
    model.component("comp2").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh2").feature("bl1").set("smoothtransition", false);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").selection().set(4);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr3", true);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr4", false);
    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/acpr", false);
    model.study("std4").feature("freq").setSolveFor("/physics/acpr2", false);
    model.study("std4").feature("freq").setSolveFor("/physics/acpr3", false);
    model.study("std4").feature("freq").setSolveFor("/physics/acpr4", true);
    model.study("std3").setGenPlots(false);
    model.study("std3")
         .label("\u7814\u7a76 3 - \u4f7f\u7528\u963b\u6297\u8fb9\u754c\u6761\u4ef6\u7684\u65e0\u6cd5\u5170\u7ba1");
    model.study("std3").feature("freq")
         .set("plist", "{10, 10.3, 10.6, 10.9, 11.2, 11.5, 11.8, 12.2, 12.5, 12.8, 13.2, 13.6, 14, 14.5, 15, 15.5, 16, 16.5, 17, 17.5, 18, 18.5, 19, 19.5, 20, 20.6, 21.2, 21.8, 22.4, 23, 23.6, 24.3, 25, 25.8, 26.5, 27.2, 28, 29, 30, 30.7, 31.5, 32.5, 33.5, 34.5, 35.5, 36.5, 37.5, 38.7, 40, 41.2, 42.5, 43.7, 45, 46.2, 47.5, 48.7, 50, 51.5, 53, 54.5, 56, 58, 60, 61.5, 63, 65, 67, 69, 71, 73, 75, 77.5, 80, 82.5, 85, 87.5, 90, 92.5, 95, 97.5, 100, 103, 106, 109, 112, 115, 118, 122, 125, 128, 132, 136, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 206, 212, 218, 224, 230, 236, 243, 250, 258, 265, 272, 280, 290, 300, 307, 315, 325, 335, 345, 355, 365, 375, 387, 400, 412, 425, 437, 450, 462, 475, 487, 500, 515, 530, 545, 560, 580, 600, 615, 630, 650, 670, 690, 710, 730, 750, 775, 800, 825, 850, 875, 900, 925, 950, 975, 1e3}");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.study("std4")
         .label("\u7814\u7a76 4 - \u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684\u65e0\u6cd5\u5170\u7ba1");
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("freq")
         .set("plist", "{10, 10.3, 10.6, 10.9, 11.2, 11.5, 11.8, 12.2, 12.5, 12.8, 13.2, 13.6, 14, 14.5, 15, 15.5, 16, 16.5, 17, 17.5, 18, 18.5, 19, 19.5, 20, 20.6, 21.2, 21.8, 22.4, 23, 23.6, 24.3, 25, 25.8, 26.5, 27.2, 28, 29, 30, 30.7, 31.5, 32.5, 33.5, 34.5, 35.5, 36.5, 37.5, 38.7, 40, 41.2, 42.5, 43.7, 45, 46.2, 47.5, 48.7, 50, 51.5, 53, 54.5, 56, 58, 60, 61.5, 63, 65, 67, 69, 71, 73, 75, 77.5, 80, 82.5, 85, 87.5, 90, 92.5, 95, 97.5, 100, 103, 106, 109, 112, 115, 118, 122, 125, 128, 132, 136, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 206, 212, 218, 224, 230, 236, 243, 250, 258, 265, 272, 280, 290, 300, 307, 315, 325, 335, 345, 355, 365, 375, 387, 400, 412, 425, 437, 450, 462, 475, 487, 500, 515, 530, 545, 560, 580, 600, 615, 630, 650, 670, 690, 710, 730, 750, 775, 800, 825, 850, 875, 900, 925, 950, 975, 1e3}");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u65e0\u6cd5\u5170\u7ba1\uff1aZ_rad vs. f");
    model.result("pg7").set("data", "none");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "f [Hz]");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "Z_rad [Pa*s/m]");
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").set("data", "dset4");
    model.result("pg7").feature("ptgr1").selection().set(2);
    model.result("pg7").feature("ptgr1").set("expr", "real(acpr3.imp1.Zn)");
    model.result("pg7").feature("ptgr1").set("linecolor", "blue");
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr1").setIndex("legends", "\u963b\u6297\u6a21\u578b real(Z)", 0);
    model.result("pg7").run();
    model.result("pg7").create("ptgr2", "PointGraph");
    model.result("pg7").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr2").set("linewidth", "preference");
    model.result("pg7").feature("ptgr2").set("data", "dset4");
    model.result("pg7").feature("ptgr2").selection().set(2);
    model.result("pg7").feature("ptgr2").set("expr", "imag(acpr3.imp1.Zn)");
    model.result("pg7").feature("ptgr2").set("linecolor", "red");
    model.result("pg7").feature("ptgr2").set("legend", true);
    model.result("pg7").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr2").setIndex("legends", "\u963b\u6297\u6a21\u578b imag(Z)", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").set("data", "dset6");
    model.result("pg7").feature("glob1").setIndex("expr", "intop2(p4)/intop2(acpr4.vz)", 0);
    model.result("pg7").feature("glob1")
         .setIndex("descr", "\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684 real(Z)", 0);
    model.result("pg7").feature("glob1").set("linestyle", "dashed");
    model.result("pg7").feature("glob1").set("linecolor", "blue");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("glob2", "Global");
    model.result("pg7").feature("glob2").set("markerpos", "datapoints");
    model.result("pg7").feature("glob2").set("linewidth", "preference");
    model.result("pg7").feature("glob2").set("data", "dset6");
    model.result("pg7").feature("glob2").setIndex("expr", "imag(intop2(p4)/intop2(acpr4.vz))", 0);
    model.result("pg7").feature("glob2")
         .setIndex("descr", "\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684 imag(Z)", 0);
    model.result("pg7").feature("glob2").set("linestyle", "dashed");
    model.result("pg7").feature("glob2").set("linecolor", "red");
    model.result("pg7").run();
    model.result("pg7").set("xlog", true);
    model.result("pg7").set("ylog", true);
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u65e0\u6cd5\u5170\u7ba1\uff1aZ_rad vs. k*a");
    model.result("pg8").set("xlabel", "k*a [-]");
    model.result("pg8").set("xlog", false);
    model.result("pg8").set("ylog", false);
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").set("xdata", "expr");
    model.result("pg8").feature("ptgr1").set("xdataexpr", "acpr3.omega/c_air*a");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr2").set("xdata", "expr");
    model.result("pg8").feature("ptgr2").set("xdataexpr", "acpr3.omega/c_air*a");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("xdata", "expr");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg8").feature("glob1").set("xdataexpr", "acpr4.omega/c_air*a");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").set("xdata", "expr");
    model.result("pg8").feature("glob2").set("xdataexpr", "acpr4.omega/c_air*a");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").setIndex("descr", "imag(Z) with PML", 0);
    model.result("pg8").feature("glob2")
         .setIndex("descr", "\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684 imag(Z)", 0);
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("descr", "real(Z) with PML", 0);
    model.result("pg8").feature("glob1")
         .setIndex("descr", "\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684 real(Z)", 0);
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "upperleft");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u6709\u6cd5\u5170\u7ba1\uff1a\u4e2d\u5fc3\u7ebf\u51fa\u53e3\u538b\u529b");
    model.result("pg9").set("data", "none");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").create("ptgr1", "PointGraph");
    model.result("pg9").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr1").set("linewidth", "preference");
    model.result("pg9").feature("ptgr1").set("data", "dset1");
    model.result("pg9").feature("ptgr1").selection().set(2);
    model.result("pg9").feature("ptgr1").set("expr", "real(acpr.p_t)");
    model.result("pg9").feature("ptgr1").set("linecolor", "blue");
    model.result("pg9").feature("ptgr1").set("legend", true);
    model.result("pg9").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg9").feature("ptgr1").setIndex("legends", "\u963b\u6297\u6a21\u578b real(p)", 0);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").create("ptgr2", "PointGraph");
    model.result("pg9").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr2").set("linewidth", "preference");
    model.result("pg9").feature("ptgr2").set("data", "dset1");
    model.result("pg9").feature("ptgr2").set("expr", "imag(acpr.p_t)");
    model.result("pg9").feature("ptgr2").set("linecolor", "red");
    model.result("pg9").feature("ptgr2").set("legend", true);
    model.result("pg9").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg9").feature("ptgr2").setIndex("legends", "\u963b\u6297\u6a21\u578b imag(p)", 0);
    model.result("pg9").run();
    model.result("pg9").feature("ptgr2").selection().set(2);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").create("ptgr3", "PointGraph");
    model.result("pg9").feature("ptgr3").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr3").set("linewidth", "preference");
    model.result("pg9").feature("ptgr3").set("data", "dset2");
    model.result("pg9").feature("ptgr3").selection().set(2);
    model.result("pg9").feature("ptgr3").set("expr", "real(acpr2.p_t)");
    model.result("pg9").feature("ptgr3").set("linecolor", "blue");
    model.result("pg9").feature("ptgr3").set("linestyle", "none");
    model.result("pg9").feature("ptgr3").set("linemarker", "circle");
    model.result("pg9").feature("ptgr3").set("markerpos", "interp");
    model.result("pg9").feature("ptgr3").set("markers", 100);
    model.result("pg9").feature("ptgr3").set("legend", true);
    model.result("pg9").feature("ptgr3").set("legendmethod", "manual");
    model.result("pg9").feature("ptgr3")
         .setIndex("legends", "\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684 real(p)", 0);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").create("ptgr4", "PointGraph");
    model.result("pg9").feature("ptgr4").set("markerpos", "datapoints");
    model.result("pg9").feature("ptgr4").set("linewidth", "preference");
    model.result("pg9").feature("ptgr4").set("data", "dset2");
    model.result("pg9").feature("ptgr4").selection().set(2);
    model.result("pg9").feature("ptgr4").set("expr", "imag(acpr2.p_t)");
    model.result("pg9").feature("ptgr4").set("linestyle", "none");
    model.result("pg9").feature("ptgr4").set("linecolor", "red");
    model.result("pg9").feature("ptgr4").set("linemarker", "circle");
    model.result("pg9").feature("ptgr4").set("markerpos", "interp");
    model.result("pg9").feature("ptgr4").set("markers", 100);
    model.result("pg9").feature("ptgr4").set("legend", true);
    model.result("pg9").feature("ptgr4").set("legendmethod", "manual");
    model.result("pg9").feature("ptgr4")
         .setIndex("legends", "\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684 imag(p)", 0);
    model.result("pg9").run();
    model.result("pg9").set("xlog", true);
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u65e0\u6cd5\u5170\u7ba1\uff1a\u4e2d\u5fc3\u7ebf\u51fa\u53e3\u538b\u529b");
    model.result("pg10").set("data", "none");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").set("data", "dset4");
    model.result("pg10").feature("ptgr1").selection().set(2);
    model.result("pg10").feature("ptgr1").set("expr", "real(acpr3.p_t)");
    model.result("pg10").feature("ptgr1").set("linecolor", "blue");
    model.result("pg10").feature("ptgr1").set("legend", true);
    model.result("pg10").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg10").feature("ptgr1").setIndex("legends", "\u963b\u6297\u6a21\u578b real(p)", 0);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("ptgr2", "PointGraph");
    model.result("pg10").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr2").set("linewidth", "preference");
    model.result("pg10").feature("ptgr2").set("data", "dset4");
    model.result("pg10").feature("ptgr2").selection().set(2);
    model.result("pg10").feature("ptgr2").set("expr", "imag(acpr3.p_t)");
    model.result("pg10").feature("ptgr2").set("linecolor", "red");
    model.result("pg10").feature("ptgr2").set("legend", true);
    model.result("pg10").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg10").feature("ptgr2").setIndex("legends", "\u963b\u6297\u6a21\u578b imag(p)", 0);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("ptgr3", "PointGraph");
    model.result("pg10").feature("ptgr3").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr3").set("linewidth", "preference");
    model.result("pg10").feature("ptgr3").set("data", "dset6");
    model.result("pg10").feature("ptgr3").selection().set(2);
    model.result("pg10").feature("ptgr3").set("expr", "real(acpr4.p_t)");
    model.result("pg10").feature("ptgr3").set("linestyle", "none");
    model.result("pg10").feature("ptgr3").set("linecolor", "blue");
    model.result("pg10").feature("ptgr3").set("linemarker", "circle");
    model.result("pg10").feature("ptgr3").set("markerpos", "interp");
    model.result("pg10").feature("ptgr3").set("legend", true);
    model.result("pg10").feature("ptgr3").set("legendmethod", "manual");
    model.result("pg10").feature("ptgr3")
         .setIndex("legends", "\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684 real(p)", 0);
    model.result("pg10").run();
    model.result("pg10").feature("ptgr3").set("markers", 100);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("ptgr4", "PointGraph");
    model.result("pg10").feature("ptgr4").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr4").set("linewidth", "preference");
    model.result("pg10").feature("ptgr4").set("data", "dset6");
    model.result("pg10").feature("ptgr4").selection().set(2);
    model.result("pg10").feature("ptgr4").set("expr", "imag(acpr4.p_t)");
    model.result("pg10").feature("ptgr4").set("linestyle", "none");
    model.result("pg10").feature("ptgr4").set("linecolor", "red");
    model.result("pg10").feature("ptgr4").set("legend", true);
    model.result("pg10").feature("ptgr4").set("legendmethod", "manual");
    model.result("pg10").feature("ptgr4")
         .setIndex("legends", "\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u7684 imag(p)", 0);
    model.result("pg10").run();
    model.result("pg10").feature("ptgr4").set("linemarker", "circle");
    model.result("pg10").feature("ptgr4").set("markerpos", "interp");
    model.result("pg10").feature("ptgr4").set("markers", 100);
    model.result("pg10").run();
    model.result("pg10").set("xlog", true);

    model.study("std1").feature("freq").setSolveFor("/physics/acpr3", false);
    model.study("std1").feature("freq").setSolveFor("/physics/acpr4", false);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr3", false);
    model.study("std2").feature("freq").setSolveFor("/physics/acpr4", false);

    model.result().dataset().remove("dset3");
    model.result().dataset().remove("dset5");
    model.result("pg4").run();

    model.title("\u5f00\u53e3\u7ba1");

    model
         .description("\u5982\u679c\u8ba1\u5212\u8bbe\u7f6e\u4e00\u4e2a\u5927\u578b\u7684\u590d\u6742\u58f0\u5b66\u6a21\u578b\uff0c\u90a3\u4e48\u5c06\u5176\u5206\u62c6\u6210\u66f4\u5c0f\u66f4\u7b80\u5355\u7684\u95ee\u9898\u5219\u4eff\u771f\u4f1a\u5bb9\u6613\u5f88\u591a\u3002\u672c\u4f8b\u4e2d\uff0c\u5706\u67f1\u7ba1\u7684\u4e00\u7aef\u5b89\u88c5\u4e86\u4e00\u4e2a\u53ef\u632f\u52a8\u7684\u6d3b\u585e\uff0c\u53e6\u4e00\u7aef\u4e3a\u5f00\u53e3\u3002\u672c\u4f8b\u6bd4\u8f83\u4e86\u5728\u5706\u67f1\u7ba1\u5f00\u53e3\u5904\u4f7f\u7528\u6307\u5b9a\u963b\u6297\u8fb9\u754c\u6761\u4ef6\u540e\u7684\u7ed3\u679c\u4ee5\u53ca\u5bf9\u6d3b\u585e\u5468\u56f4\u7a7a\u6c14\u4f7f\u7528\u5b8c\u7f8e\u5339\u914d\u5c42\u6761\u4ef6\u540e\u7684\u7ed3\u679c\u3002\u5bf9\u6321\u677f\u4e2d\u5b89\u88c5\u7684\u7ba1\u9053\uff08\u5f2f\u7ba1\uff09\u4ee5\u53ca\u672a\u6298\u5f2f\u7684\u7ba1\u9053\u90fd\u505a\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("open_pipe.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
