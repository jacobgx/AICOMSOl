/*
 * three_phase_motor_frequency.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class three_phase_motor_frequency {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Verifications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mf", true);

    model.param().set("f0", "60[Hz]");
    model.param().descr("f0", "\u7535\u6e90\u9891\u7387");
    model.param().set("w0", "2*pi*f0");
    model.param().descr("w0", "\u7535\u6e90\u89d2\u9891\u7387");
    model.param().set("n0", "1000");
    model.param().descr("n0", "\u531d\u6570");
    model.param().set("L", "1[m]");
    model.param().descr("L", "\u7535\u673a\u7684\u957f\u5ea6");
    model.param().set("Omega", "200[rad/s]");
    model.param().descr("Omega", "\u8f6c\u5b50\u7684\u89d2\u901f\u5ea6");
    model.param().set("coil_wire_current", "2045.175[A]*sqrt(2)/n0");
    model.param().descr("coil_wire_current", "\u7ebf\u5708\u7535\u6d41\u5e45\u503c");

    model.component("comp1").geom("geom1").insertFile("three_phase_motor_frequency_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(19);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206\uff0c\u94a2");
    model.component("comp1").cpl("intop1").set("opname", "int_steel");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().set(18);
    model.component("comp1").cpl("intop2").label("\u79ef\u5206\uff0c\u94dd");
    model.component("comp1").cpl("intop2").set("opname", "int_al");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");
    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().set(1, 2);
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Cylindrical");

    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").selection().set(15, 18, 19);
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().set(4, 13);
    model.component("comp1").physics("mf").feature("coil1").label("\u7ebf\u5708\uff0cA \u76f8");
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("coilGroup", true);
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "coil_wire_current");
    model.component("comp1").physics("mf").feature("coil1").set("N", "n0");
    model.component("comp1").physics("mf").feature("coil1").create("rcd1", "ReverseCoilGroupDomain", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("rcd1").selection().set(13);
    model.component("comp1").physics("mf").feature().duplicate("coil2", "coil1");
    model.component("comp1").physics("mf").feature("coil2").label("\u7ebf\u5708\uff0cB \u76f8");
    model.component("comp1").physics("mf").feature("coil2").selection().set(7, 9);
    model.component("comp1").physics("mf").feature("coil2").feature("rcd1").selection().set(7);
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "coil_wire_current*exp(j*2*pi/3)");
    model.component("comp1").physics("mf").feature().duplicate("coil3", "coil2");
    model.component("comp1").physics("mf").feature("coil3").label("\u7ebf\u5708\uff0cC \u76f8");
    model.component("comp1").physics("mf").feature("coil3").selection().set(5, 11);
    model.component("comp1").physics("mf").feature("coil3").set("ICoil", "coil_wire_current*exp(-j*2*pi/3)");
    model.component("comp1").physics("mf").feature("coil3").feature("rcd1").selection().set(5);
    model.component("comp1").physics("mf").create("vlt1", "Velocity", 2);
    model.component("comp1").physics("mf").feature("vlt1").selection().set(18, 19);
    model.component("comp1").physics("mf").feature("vlt1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("mf").feature("vlt1").set("v", new String[]{"0", "Omega*sys2.r", "0"});
    model.component("comp1").physics("mf").create("fcal1", "ForceCalculation", 2);
    model.component("comp1").physics("mf").feature("fcal1").selection().set(18, 19);

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
    model.component("comp1").material("mat1").selection().set(4, 5, 7, 9, 11, 13);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u94dd");
    model.component("comp1").material("mat2").selection().set(18);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.72e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u8f6c\u5b50\u94a2");
    model.component("comp1").material("mat3").selection().set(19);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"30"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.6e6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u5b9a\u5b50\u94a2");
    model.component("comp1").material("mat4").selection().set(15);
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermeability", new String[]{"30"});
    model.component("comp1").material("mat4").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat4").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "f0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").feature("param").setIndex("pname", "f0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").feature("param").setIndex("pname", "Omega", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,20,1200)", 0);
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
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
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Az");
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
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "\\OMEGA: eval(Omega) rad/s \u8868\u9762\uff1a\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>) \u7ebf\uff1a\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "mf.Jz");
    model.result("pg2").feature("surf1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\uff0cz \u5206\u91cf");
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "mf.Az");
    model.result("pg2").feature("con1").set("descr", "\u78c1\u77e2\u52bf\uff0cz \u5206\u91cf");
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("color", "gray");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 20, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 41, 0);
    model.result("pg2").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u53c2\u8003\u6570\u636e");
    model.result().table("tbl1").importData("three_phase_motor_frequency_data.txt");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u626d\u77e9");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u626d\u77e9 (N*m)");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u89d2\u901f\u5ea6 (rad/s)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"mf.Tz_0"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u626d\u77e9\uff0cz \u5206\u91cf"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"N*m"});
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u8ba1\u7b97\u6570\u636e", 0);
    model.result("pg3").run();
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg3").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg3").feature("tblp1").set("linestyle", "none");
    model.result("pg3").feature("tblp1").set("linemarker", "cycle");
    model.result("pg3").feature("tblp1").set("legend", true);
    model.result("pg3").feature("tblp1").set("legendmethod", "manual");
    model.result("pg3").feature("tblp1").setIndex("legends", "\u53c2\u8003\u6570\u636e", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u7ebf\u5708\u7535\u538b");
    model.result("pg4").set("title", "\u6bcf\u531d\u7684\u7ebf\u5708\u7535\u538b (V, RMS)");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "abs(mf.coil1.Vind)/(n0*sqrt(2))", 0);
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u8f6c\u5b50\u635f\u8017");
    model.result("pg5").set("title", "\u8f6c\u5b50\u635f\u8017 (W)");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "int_steel(mf.Qh*L)+int_al(mf.Qh*L)", 0);
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u94a2\u635f\u8017");
    model.result("pg6").set("title", "\u94a2\u635f\u8017 (W)");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "int_steel(mf.Qh*L)", 0);
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("plotcolumns", new int[]{5});
    model.result("pg6").run();
    model.result("pg2").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
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
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").set("repeat", "forever");
    model.result("pg2").run();

    model.title("\u4e09\u76f8\u7535\u673a\u7684\u9891\u57df\u7814\u7a76");

    model
         .description("\u8fd9\u4e2a\u4e09\u76f8\u611f\u5e94\u7535\u673a\u6a21\u578b\u7528\u6765\u4e0e\u201c\u6d4b\u8bd5\u7535\u78c1\u5206\u6790\u65b9\u6cd5\u201d(TEAM) \u7814\u8ba8\u4f1a\u95ee\u9898 30 \u8fdb\u884c\u6bd4\u8f83\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u201c\u78c1\u573a\u201d\u7269\u7406\u573a\u5728 60\u00a0Hz \u7684\u9891\u57df\u4e2d\u5bf9\u7535\u673a\u5efa\u6a21\uff0c\u5e76\u4f7f\u7528\u901f\u5ea6\uff08\u6d1b\u4f26\u5179\u9879\uff09\u7279\u5f81\u5bf9\u7535\u67a2\u7684\u65cb\u8f6c\u8fdb\u884c\u5efa\u6a21\u3002\n\n\u6c42\u89e3\u6a21\u578b\u53ef\u4ee5\u751f\u6210\u7535\u673a\u5728\u4e0d\u540c\u8f6c\u5b50\u901f\u5ea6\u4e0b\u7684\u7535\u78c1\u8f6c\u77e9\u3001\u611f\u5e94\u7535\u538b\u548c\u8f6c\u5b50\u635f\u8017\u503c\u3002\u7136\u540e\u5c06\u8fd9\u4e9b\u8ba1\u7b97\u7ed3\u679c\u4e0e TEAM \u95ee\u9898\u7684\u5206\u6790\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("three_phase_motor_frequency.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
