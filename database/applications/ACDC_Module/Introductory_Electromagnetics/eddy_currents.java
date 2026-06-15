/*
 * eddy_currents.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class eddy_currents {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Electromagnetics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mf", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 250);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 300);
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl1").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new int[]{300, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{10, 100, 60});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{145, -50, 70});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u677f");
    model.component("comp1").selection("sel1").set(2);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u677f\u8fb9\u754c");
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection("sel2").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel2").set(2);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("sel1");

    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").selection().named("sel1");
    model.component("comp1").physics("mf").create("edc1", "EdgeCurrent", 1);
    model.component("comp1").physics("mf").feature("edc1").selection().set(6);
    model.component("comp1").physics("mf").feature("edc1").set("Ie", "sqrt(2)*24[kA]");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("yscale", 0.4);
    model.component("comp1").mesh("mesh1").feature("ftet1").set("zscale", 0.4);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 5);
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", 50);
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
    model.result("pg1").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().named("sel1");

    model.component("comp1").view("view1").set("transparency", false);
    model.component("comp1").view("view1").set("renderwireframe", true);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u6da1\u6d41\u548c\u70ed");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("expr", "mf.Qrh");
    model.result("pg2").feature("slc1").set("descr", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535");
    model.result("pg2").feature("slc1").set("quickxmethod", "coord");
    model.result("pg2").feature("slc1").set("quickx", 154.9);
    model.result("pg2").feature("slc1").set("colortable", "HeatCamera");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"mf.Jx", "mf.Jy", "mf.Jz"});
    model.result("pg2").feature("str1").set("descr", "\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.06);
    model.result("pg2").feature("str1").set("color", "black");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().numerical().create("int1", "IntVolume");
    model.result().numerical("int1").selection().named("sel1");
    model.result().numerical("int1").set("expr", new String[]{"mf.Qrh"});
    model.result().numerical("int1").set("descr", new String[]{"\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535"});
    model.result().numerical("int1").set("unit", new String[]{"W"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u4f53\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();

    model.component("comp1").physics("mf").selection().set(1);
    model.component("comp1").physics("mf").create("imp1", "Impedance", 2);
    model.component("comp1").physics("mf").feature("imp1").selection().named("sel2");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Iron");
    model.component("comp1").material("mat2").set("family", "iron");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"4000", "0", "0", "0", "4000", "0", "0", "0", "4000"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7870[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.29");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("sel2");

    model.sol("sol1").detach();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().named("sel2");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u8868\u9762\u635f\u8017\u5bc6\u5ea6\uff0c\u7535 (mf)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "mf.Qsrh");
    model.result("pg3").feature("surf1").set("descr", "\u8868\u9762\u635f\u8017\u5bc6\u5ea6\uff0c\u7535");
    model.result("pg3").feature("surf1").set("colortable", "HeatCamera");
    model.result("pg3").run();
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").set("data", "dset3");
    model.result().numerical("int2").selection().named("sel2");
    model.result().numerical("int2").set("expr", new String[]{"mf.Qsrh"});
    model.result().numerical("int2").set("descr", new String[]{"\u8868\u9762\u635f\u8017\u5bc6\u5ea6\uff0c\u7535"});
    model.result().numerical("int2").set("unit", new String[]{"W"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").setResult();
    model.result("pg3").run();

    model.title("\u6da1\u6d41");

    model
         .description("\u5728\u672c\u4f8b\u4e2d\uff0c\u4e00\u5757\u91d1\u5c5e\u677f\u653e\u7f6e\u5728 50\u00a0Hz \u4ea4\u6d41\u5bfc\u7ebf\u9644\u8fd1\uff0c\u677f\u4e2d\u7531\u6b64\u4ea7\u751f\u7684\u6da1\u6d41\u5206\u5e03\u53d6\u51b3\u4e8e\u677f\u7684\u7535\u5bfc\u7387\u548c\u78c1\u5bfc\u7387\u3002\u5176\u4e2d\u8003\u8651\u4e86\u56db\u79cd\u4e0d\u540c\u7684\u6750\u6599\uff1a\u94dc\u3001\u94dd\u3001\u4e0d\u9508\u94a2\u548c\u78c1\u94c1\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("eddy_currents.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
