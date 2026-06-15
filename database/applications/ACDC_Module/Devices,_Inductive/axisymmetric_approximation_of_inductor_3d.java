/*
 * axisymmetric_approximation_of_inductor_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class axisymmetric_approximation_of_inductor_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Inductive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mef", "ElectricInductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mef", true);

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").create("imp1", "Import");
    model.geom("part1").feature("imp1").set("filename", "inductor_3d.mphbin");
    model.geom("part1").feature("imp1").importData();
    model.geom("part1").create("wp1", "WorkPlane");
    model.geom("part1").feature("wp1").set("unite", true);
    model.geom("part1").feature("wp1").set("quickplane", "xz");
    model.geom("part1").feature("wp1").geom().create("cro1", "CrossSection");
    model.geom("part1").feature("wp1").geom().run("cro1");
    model.geom("part1").run("wp1");
    model.geom("part1").create("del1", "Delete");
    model.geom("part1").feature("del1").selection("input").init(3);
    model.geom("part1").feature("del1").selection("input").set("imp1", 2, 3, 4, 5);
    model.geom("part1").run("del1");
    model.geom("part1").create("wp2", "WorkPlane");
    model.geom("part1").feature("wp2").set("unite", true);
    model.geom("part1").feature("wp2").set("quickz", "5[mm]");
    model.geom("part1").feature("wp2").geom().create("cro1", "CrossSection");
    model.geom("part1").feature("wp2").geom().run("cro1");
    model.geom("part1").run("wp2");
    model.geom("part1").create("wp3", "WorkPlane");
    model.geom("part1").feature("wp3").set("unite", true);
    model.geom("part1").feature("wp3").set("planetype", "faceparallel");
    model.geom("part1").feature("wp3").selection("face").set("del1", 46);
    model.geom("part1").feature("wp3").geom().create("cro1", "CrossSection");
    model.geom("part1").feature("wp3").geom().run("cro1");
    model.geom("part1").run("wp3");

    model.view("view2").set("renderwireframe", true);

    model.geom("part1").run("wp3");
    model.geom("part1").measure().selection().init(0);
    model.geom("part1").measure().selection().set("wp2", 9, 14);
    model.geom("part1").measure().selection().init(0);
    model.geom("part1").measure().selection().set("wp2", 7);
    model.geom("part1").measure().selection().set("wp3", 8);
    model.geom("part1").measure().selection().init(2);
    model.geom("part1").measure().selection().set("wp2", 1, 2, 5, 6);
    model.geom("part1").measure().selection().init(2);
    model.geom("part1").measure().selection().set("wp3", 1, 5);

    model.param().set("inner_diameter", "0.07[m]");
    model.param().descr("inner_diameter", "\u4e2d\u5fc3\u67f1\u7684\u4e09\u7ef4\u76f4\u5f84");
    model.param().set("outer_diameter", "0.13[m]");
    model.param().descr("outer_diameter", "\u5916\u67f1\u4e4b\u95f4\u7684\u4e09\u7ef4\u5185\u90e8\u8ddd\u79bb");
    model.param().set("external_area", "0.001992[m^2]");
    model.param().descr("external_area", "\u4e24\u4e2a\u6a2a\u5411\u67f1\u4e4b\u4e00\u7684\u4e09\u7ef4\u9762\u79ef");
    model.param().set("upper_area", "7.5e-4[m^2]");
    model.param().descr("upper_area", "\u4e0a\u78c1\u8def\u95ed\u5408\u7684\u4e09\u7ef4\u9762\u79ef");
    model.param().set("r_in", "outer_diameter/2");
    model.param()
         .descr("r_in", "\u6a2a\u5411\u67f1\u5916\u534a\u5f84\u7684\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u7b49\u6548\u534a\u5f84");
    model.param().set("r_out", "sqrt(external_area/pi+r_in^2)");
    model.param()
         .descr("r_out", "\u6a2a\u5411\u67f1\u5916\u534a\u5f84\u7684\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u7b49\u6548\u534a\u5f84");
    model.param().set("h_eq", "upper_area/pi/r_in");
    model.param()
         .descr("h_eq", "\u4e0a\u78c1\u8def\u95ed\u5408\u9ad8\u5ea6\u7684\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u7b49\u6548\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "sequence");
    model.component("comp1").geom("geom1").feature("imp1").set("sequence", "part1/wp1");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r_out", "0.0325+2*h_eq"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-0.004-h_eq"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.1);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{0, 0.01});
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("imp1", "r1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mef").create("alcs1", "ElectromagneticModelSolid", 2);
    model.component("comp1").physics("mef").feature("alcs1").selection().set(2);
    model.component("comp1").physics("mef").feature("alcs1").set("ConstitutiveRelationBH", "MagneticLosses");
    model.component("comp1").physics("mef").feature("mi1").create("ein1", "ElectricInsulation", 1);
    model.component("comp1").physics("mef").feature("mi1").feature("ein1").selection().set(25, 26);
    model.component("comp1").physics("mef").create("al1", "AmperesLaw", 2);
    model.component("comp1").physics("mef").feature("al1").selection().set(4, 5, 6);
    model.component("comp1").physics("mef").create("rlccg1", "RLCCoilGroup", 2);
    model.component("comp1").physics("mef").feature("rlccg1").selection().set(4, 5, 6);
    model.component("comp1").physics("mef").feature("rlccg1").set("DomainSpec", "manual");
    model.component("comp1").physics("mef").feature("rlccg1").set("DomainsList", "5, 4, 6");
    model.component("comp1").physics("mef").feature("rlccg1").set("V0", "1[mV]");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material("mat1").propertyGroup()
         .create("MagneticLosses", "MagneticLosses", "Magnetic_losses");
    model.component("comp1").material("mat1").propertyGroup("MagneticLosses").set("murPrim", new String[]{"1200"});
    model.component("comp1").material("mat1").propertyGroup("MagneticLosses").set("murBis", new String[]{"100"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(4, 5, 6);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"6e7"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2, 3, 4, 5, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "2e-3");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(4, 5, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "5e-4");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(4, 5, 6);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().all();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 12);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "10[um]");
    model.component("comp1").mesh("mesh1").run("bl1");

    model.study("std1").feature("freq").set("punit", "MHz");
    model.study("std1").feature("freq").set("plist", "range(1,0.25,10)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mef)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("expr", "mef.normB");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("expr", "mef.normB");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mef.Psi");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6\uff0c\u56de\u8f6c\u51e0\u4f55 (mef)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "mef.normB");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("vol1").set("colorcalibration", -0.8);
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("solutionparams", "parent");
    model.result("pg2").feature("con1").set("expr", "mef.Psi");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("number", 10);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg2").feature("con1").set("resolution", "fine");
    model.result("pg2").feature("con1").set("inheritcolor", false);
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "vol1");
    model.result("pg2").feature("con1").feature().create("filt1", "Filter");
    model.result("pg2").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg2").feature("con1").feature("filt1").set("shownodespec", "on");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (mef)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "mef.normE");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"mef.Er", "mef.Ez"});
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.02);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "mef.normE");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(2, 4, 5, 6);
    model.result("pg2").run();
    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(3, 4, 5, 6);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u963b\u635f\u8017");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "log(mef.Qrh+0.1)");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u573a");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("xnumber", 20);
    model.result("pg5").feature("arws1").set("ynumber", 20);
    model.result("pg5").feature("arws1").set("expr", new String[]{"mef.Er", "mef.Ez"});
    model.result("pg5").feature("arws1").set("descr", "\u7535\u573a");
    model.result("pg5").feature("arws1").create("col1", "Color");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 22, 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u963b\u6297");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").set("twoyaxes", true);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").run();
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "mef.RCoil_1", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u963b\u6297\u7684\u5b9e\u90e8", 0);
    model.result("pg6").run();
    model.result("pg6").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg6").feature("glob2").setIndex("expr", "mef.XCoil_1", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "\u963b\u6297\u7684\u865a\u90e8", 0);
    model.result("pg6").run();

    model.title("\u4e09\u7ef4\u7535\u611f\u5668\u6a21\u578b\u7684\u8f74\u5bf9\u79f0\u8fd1\u4f3c\u5206\u6790");

    model
         .description("\u5728\u9ad8\u9891\u4e0b\uff0c\u7535\u611f\u5143\u4ef6\u7684\u5bfc\u7ebf\u4e4b\u95f4\u4f1a\u4ea7\u751f\u7535\u5bb9\u8026\u5408\u3002\u5bf9\u8fd9\u79cd\u73b0\u8c61\u5efa\u6a21\u65f6\u9700\u8981\u63cf\u8ff0\u7535\u573a\uff0c\u5176\u4e2d\u5305\u542b\u4e0e\u5bfc\u7ebf\u5e73\u884c\u548c\u5782\u76f4\u7684\u5206\u91cf\u3002\u8003\u8651\u5230\u8fd9\u4e00\u70b9\uff0c\u6211\u4eec\u53ef\u80fd\u4f1a\u5f97\u51fa\u8fd9\u6837\u7684\u7ed3\u8bba\uff1a\u5373\u4f7f\u7ebf\u5708\u4e3a\u87ba\u65cb\u5f62\uff0c\u4e5f\u603b\u662f\u9700\u8981\u4e09\u7ef4\u6a21\u578b\u624d\u80fd\u6a21\u62df\u8fd9\u79cd\u73b0\u8c61\uff1b\u7136\u800c\uff0c\u5b9e\u9645\u60c5\u51b5\u5e76\u975e\u5982\u6b64\u3002\n\n\u8be5\u4e09\u7ef4\u7535\u611f\u5668\u793a\u4f8b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u8f74\u5bf9\u79f0\u4eff\u771f\uff0c\u63d0\u53d6\u4e0e\u4e09\u7ef4\u7535\u611f\u5668\u7684\u81ea\u8c10\u632f\u76f8\u5173\u7684\u4fe1\u606f\u3002\u4e3a\u4e86\u5efa\u7acb\u6b63\u786e\u7684\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\uff0c\u6211\u4eec\u521b\u5efa\u4e86\u6709\u6548\u7684\u8f74\u5bf9\u79f0\u94c1\u82af\uff0c\u5e76\u4f7f\u7528\u4e86 RLC \u7ebf\u5708\u7ec4 \u7279\u5f81\u3002\u8fd9\u79cd\u7cbe\u76ca\u65b9\u6cd5\u7279\u522b\u9002\u7528\u4e8e\u7814\u7a76\u5177\u6709\u6570\u5343\u531d\u7ebf\u5708\u7684\u7cfb\u7edf\uff0c\u4f8b\u5982\u4f20\u611f\u5668\u6216\u53d8\u538b\u5668\uff0c\u4ece\u800c\u4fdd\u6301\u8f83\u4f4e\u7684\u8ba1\u7b97\u6210\u672c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("axisymmetric_approximation_of_inductor_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
