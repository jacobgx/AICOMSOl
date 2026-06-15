/*
 * magnetotellurics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:21 by COMSOL 6.3.0.290. */
public class magnetotellurics {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Resistive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("mf2", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mf", true);
    model.study("std1").feature("freq").setSolveFor("/physics/mf2", true);

    model.param().set("Lx", "70[km]");
    model.param().descr("Lx", "x \u65b9\u5411\u7684\u57df\u5927\u5c0f");
    model.param().set("Ly", "70[km]");
    model.param().descr("Ly", "y \u65b9\u5411\u7684\u57df\u5927\u5c0f");
    model.param().set("Lh", "20[km]");
    model.param().descr("Lh", "\u5e95\u5c42\u7684\u9ad8\u5ea6");
    model.param().set("h_box", "10[km]");
    model.param().descr("h_box", "\u6846\u9ad8");
    model.param().set("w_box", "20[km]");
    model.param().descr("w_box", "\u6846\u5bbd");
    model.param().set("d_box", "40[km]");
    model.param().descr("d_box", "\u6846\u6df1");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Lx", "Ly", "Lh"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-2*Lh"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"Lx", "Ly", "Lh"});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "-Lh"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"Lx", "Ly", "h_box"});
    model.component("comp1").geom("geom1").feature("blk3").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "0", "-h_box/2"});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"w_box", "d_box", "h_box"});
    model.component("comp1").geom("geom1").feature("blk4").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new String[]{"-w_box/2", "0", "-h_box/2"});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"w_box", "d_box", "h_box"});
    model.component("comp1").geom("geom1").feature("blk5").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new String[]{"w_box/2", "0", "-h_box/2"});
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("rho_xy", "((abs(mf.Ex/mf.Hy))^2/(2*pi*freq*mu0_const))", "\u89c6\u7535\u963b\u7387\uff0cxy");
    model.component("comp1").variable("var1")
         .set("rho_yx", "((abs(mf2.Ey/mf2.Hx))^2/(2*pi*freq*mu0_const))", "\u89c6\u7535\u963b\u7387\uff0cyx");
    model.component("comp1").variable("var1")
         .set("rho_xx", "((abs(mf2.Ex/mf2.Hx))^2/(2*pi*freq*mu0_const))", "\u89c6\u7535\u963b\u7387\uff0cxx");
    model.component("comp1").variable("var1")
         .set("rho_yy", "((abs(mf.Ey/mf.Hy))^2/(2*pi*freq*mu0_const))", "\u89c6\u7535\u963b\u7387\uff0cyy");
    model.component("comp1").variable("var1")
         .set("phi_xy", "arg(1[S]*mf.Ex/mf.Hy)[rad]+180[deg]", "\u89c6\u7535\u963b\u7387\u76f8\uff0cxy");
    model.component("comp1").variable("var1")
         .set("phi_yx", "arg(1[S]*mf2.Ey/mf2.Hx)[rad]+180[deg]", "\u89c6\u7535\u963b\u7387\u76f8\uff0cyx");
    model.component("comp1").variable("var1")
         .set("phi_xx", "arg(1[S]*mf2.Ex/mf2.Hx)[rad]+180[deg]", "\u89c6\u7535\u963b\u7387\u76f8\uff0cxx");
    model.component("comp1").variable("var1")
         .set("phi_yy", "arg(1[S]*mf.Ey/mf.Hy)[rad]+180[deg]", "\u89c6\u7535\u963b\u7387\u76f8\uff0cyy");
    model.component("comp1").variable("var1")
         .set("S", "abs((mf2.Ex/mf2.Hx+mf.Ey/mf.Hy)/(mf.Ex/mf.Hy-mf2.Ey/mf2.Hx))", "\u963b\u6297\u504f\u79bb\u5ea6");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1, 4, 7, 25, 26, 27);
    model.component("comp1").selection("sel1").label("x \u8fb9\u754c");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(2, 5, 8, 11, 12, 13);
    model.component("comp1").selection("sel2").label("y \u8fb9\u754c");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(10, 17, 22);
    model.component("comp1").selection("sel3").label("\u9876\u90e8");

    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").selection().all();
    model.component("comp1").physics("mf").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("mf").feature("symp1").selection().named("sel2");
    model.component("comp1").physics("mf").feature("symp1").set("Symmetry_type", "Antisymmetry");
    model.component("comp1").physics("mf").create("mfb1", "MagneticFieldBoundary", 2);
    model.component("comp1").physics("mf").feature("mfb1").selection().named("sel3");
    model.component("comp1").physics("mf").feature("mfb1").set("H0", new int[]{0, 1000, 0});
    model.component("comp1").physics("mf2").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf2").feature("als1").selection().all();
    model.component("comp1").physics("mf2").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("mf2").feature("symp1").selection().named("sel1");
    model.component("comp1").physics("mf2").feature("symp1").set("Symmetry_type", "Antisymmetry");
    model.component("comp1").physics("mf2").create("mfb1", "MagneticFieldBoundary", 2);
    model.component("comp1").physics("mf2").feature("mfb1").selection().named("sel3");
    model.component("comp1").physics("mf2").feature("mfb1").set("H0", new int[]{1000, 0, 0});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(2, 5);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0.01"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").label("\u5ca9\u77f3 100 ohmm");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0.1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat2").label("\u5ca9\u77f3 10 ohmm");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(4);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat3").label("\u5ca9\u77f3 1 ohmm");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").selection().set(1);
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("electricconductivity", new String[]{"10"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat4").label("\u5ca9\u77f3 0.1 ohmm");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 2, 4, 5, 7, 8);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("cpf1", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("source").set(1, 4, 7);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("destination").set(25, 26, 27);
    model.component("comp1").mesh("mesh1").create("cpf2", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("source").set(2, 5, 8);
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("destination").set(11, 12, 13);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(4, 5);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "0.1 0.01");
    model.study("std1").feature("freq").setSolveFor("/physics/mf2", false);
    model.study("std1").create("freq2", "Frequency");
    model.study("std1").feature("freq2").set("plist", "0.1 0.01");
    model.study("std1").feature("freq2").setSolveFor("/physics/mf", false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v2").set("control", "user");
    model.sol("sol1").feature("v2").set("initmethod", "init");
    model.sol("sol1").feature("v2").set("initsol", "zero");
    model.sol("sol1").feature("v2").set("notsolnum", "all");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "mf.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "mf.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "mf.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Prism");
    model.result("pg1").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "mf.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "mf.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "mf.CPz");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg1").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6 (mf2)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "mf2.normB");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "mf2.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "mf2.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "mf2.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"mf2.Bx", "mf2.By", "mf2.Bz"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "mf2.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "mf2.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "mf2.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "mf2.normB");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "rho_xy");
    model.result("pg3").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("titletype", "label");
    model.result("pg3").label("\u89c6\u5728\u7535\u963b\u7387 xy \u5e73\u9762");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("showlegendsunit", true);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "rho_yx");
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "label");
    model.result("pg4").label("\u89c6\u5728\u7535\u963b\u7387 yx \u5e73\u9762");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", -35000, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 35000, 1, 0);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "cln1");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "rho_xy");
    model.result("pg5").feature("lngr1").set("descr", "\u89c6\u7535\u963b\u7387\uff0cxy");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("xdatadescr", "x \u5750\u6807");
    model.result("pg5").feature("lngr1").set("xdataunit", "km");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg5").feature("lngr1").set("legendpattern", "rho_xy, eval(freq) Hz");
    model.result("pg5").run();
    model.result("pg5").set("ylog", true);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("expr", "rho_yx");
    model.result("pg5").feature("lngr2").set("descr", "\u89c6\u7535\u963b\u7387\uff0cyx");
    model.result("pg5").feature("lngr2").set("legendpattern", "rho_yx, eval(freq) Hz");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("titletype", "label");
    model.result("pg5").label("\u89c6\u5728\u7535\u963b\u7387\u8de8\u8d8a\u7a81\u53d8");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u7535\u963b\u7387 (\u03a9*m)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u96c6\u80a4\u6df1\u5ea6");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("slc1", "Slice");
    model.result("pg6").feature("slc1").set("expr", "mf.deltaS");
    model.result("pg6").feature("slc1").set("descr", "\u96c6\u80a4\u6df1\u5ea6");
    model.result("pg6").feature("slc1").set("unit", "km");
    model.result("pg6").feature("slc1").set("quickplane", "zx");
    model.result("pg6").feature("slc1").set("quickynumber", 1);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("H \u503e\u5b50\u56fe z \u5206\u91cf");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "mf.Hz");
    model.result("pg7").feature("surf1").set("descr", "\u78c1\u573a\uff0cz \u5206\u91cf");
    model.result("pg7").run();

    model.title("\u5927\u5730\u7535\u78c1\u6cd5");

    model
         .description("\u5927\u5730\u7535\u78c1\u6cd5\u662f\u4e00\u79cd\u5229\u7528\u7535\u79bb\u5c42\u63d0\u4f9b\u7684\u5929\u7136\u7535\u78c1\u6e90\u6765\u8ba1\u7b97\u5730\u7403\u5730\u4e0b\u7535\u963b\u7387\u5206\u5e03\u7684\u65b9\u6cd5\u3002\u672c\u4f8b\u5728 COMSOL \u4e2d\u5b9e\u73b0\uff0c\u7814\u7a76\u7531 Zhdanov \u7b49\u4eba\u5b9a\u4e49\u7684\u6807\u51c6\u5927\u5730\u7535\u78c1\u57fa\u51c6\u6a21\u578b COMMEMI-3D-2\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("magnetotellurics.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
