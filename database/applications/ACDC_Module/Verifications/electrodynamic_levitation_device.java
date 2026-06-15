/*
 * electrodynamic_levitation_device.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class electrodynamic_levitation_device {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Verifications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mf", true);
    model.study("std1").feature("time").setSolveFor("/physics/ge", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "50[Hz]", "\u7535\u6e90\u9891\u7387");
    model.param().set("Ni", "960", "\u5185\u7ebf\u5708\u531d\u6570");
    model.param().set("No", "576", "\u5916\u7ebf\u5708\u531d\u6570");
    model.param().set("sigma", "3.4e7[S/m]", "\u94dd\u7684\u7535\u5bfc\u7387");
    model.param().set("I0", "20[A]", "\u7ebf\u5708\u7535\u6d41");
    model.param().set("d_wire", "1.2[mm]", "\u7ebf\u5708\u7ebf\u5f84");
    model.param().set("M_disc", "0.107[kg]", "\u94dd\u76d8\u8d28\u91cf");
    model.param().set("z0", "3.8[mm]", "\u94dd\u76d8\u521d\u59cb\u4f4d\u7f6e");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u7ebf\u5708 1");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{28, 52});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{41, -26});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").label("\u7ebf\u5708 2");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{15, 52});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{87.5, -26});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"120", "50-2.5"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, 2.5});
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", "(39-3)/2-2.5", 0);
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", 3, 1);
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", "40-(39+3)/2", 2);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"120", "50-2.5"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new double[]{0, 2.5});
    model.component("comp1").geom("geom1").feature("r4").setIndex("layer", 65, 0);
    model.component("comp1").geom("geom1").feature("r4").setIndex("layer", 45, 1);
    model.component("comp1").geom("geom1").feature("r4").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r4").set("layerleft", true);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new double[]{120, 72.5});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new int[]{0, -70});
    model.component("comp1").geom("geom1").feature("r5").setIndex("layer", 10, 0);
    model.component("comp1").geom("geom1").feature("r5").set("layerright", true);
    model.component("comp1").geom("geom1").run("r5");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u65e0\u9650\u5143\u57df");
    model.component("comp1").selection("sel1").set(1, 6, 11, 13, 14, 15, 16, 17, 18);

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().named("sel1");
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Cylindrical");

    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").selection().set(4);
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").selection().set(7);
    model.component("comp1").physics("mf").feature("coil1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "I0*sin(2*pi*f0*t)");
    model.component("comp1").physics("mf").feature("coil1").set("N", "Ni");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "Diameter");
    model.component("comp1").physics("mf").feature("coil1").set("coilWindDiameter", "d_wire");
    model.component("comp1").physics("mf").create("coil2", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil2").selection().set(12);
    model.component("comp1").physics("mf").feature("coil2").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("mf").feature("coil2").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "-I0*sin(2*pi*f0*t)");
    model.component("comp1").physics("mf").feature("coil2").set("N", "No");
    model.component("comp1").physics("mf").feature("coil2").set("AreaFrom", "Diameter");
    model.component("comp1").physics("mf").feature("coil2").set("coilWindDiameter", "d_wire");
    model.component("comp1").physics("mf").create("fcal1", "ForceCalculation", 2);
    model.component("comp1").physics("mf").feature("fcal1").selection().set(4);

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
    model.component("comp1").material("mat1").selection().set(7, 12);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u94dd");
    model.component("comp1").material("mat2").selection().set(4);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("F_g", "M_disc*g_const");
    model.component("comp1").variable("var1").descr("F_g", "\u91cd\u529b");

    model.component("comp1").physics("ge").label("\u94dd\u677f\u52a8\u529b\u5b66");
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "velocity");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "acceleration");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "v", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "d(v,t)+(F_g-mf.Forcez_0)/M_disc", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "\u94dd\u677f\u901f\u5ea6", 0, 0);
    model.component("comp1").physics("ge").create("ge2", "GlobalEquations", -1);
    model.component("comp1").physics("ge").feature("ge2").set("DependentVariableQuantity", "displacement");
    model.component("comp1").physics("ge").feature("ge2").set("SourceTermQuantity", "velocity");
    model.component("comp1").physics("ge").feature("ge2").setIndex("name", "u", 0, 0);
    model.component("comp1").physics("ge").feature("ge2").setIndex("equation", "ut-v", 0, 0);
    model.component("comp1").physics("ge").feature("ge2").setIndex("initialValueU", "z0", 0, 0);
    model.component("comp1").physics("ge").feature("ge2").setIndex("description", "\u94dd\u677f\u4f4d\u7f6e", 0, 0);

    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").label("z \u53c2\u6570\u5316\uff0c\u9876\u90e8");
    model.component("comp1").variable("var2").selection().set(5, 10);
    model.component("comp1").variable("var2").set("s2", "(40[mm]-Z)/(40[mm]-21[mm])");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("z \u53c2\u6570\u5316\uff0c\u4e2d\u5fc3");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().set(4, 9);
    model.component("comp1").variable("var3").set("s2", "1");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("z \u53c2\u6570\u5316\uff0c\u5e95\u90e8");
    model.component("comp1").variable("var4").selection().geom("geom1", 2);
    model.component("comp1").variable("var4").selection().set(3, 8);
    model.component("comp1").variable("var4").set("s2", "(Z-2.5[mm])/(18[mm]-2.5[mm])");
    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").label("r \u53c2\u6570\u5316\uff0c\u5de6\u4fa7");
    model.component("comp1").variable("var5").selection().geom("geom1", 2);
    model.component("comp1").variable("var5").selection().set(3, 4, 5);
    model.component("comp1").variable("var5").set("s1", "1");
    model.component("comp1").variable().create("var6");
    model.component("comp1").variable("var6").label("r \u53c2\u6570\u5316\uff0c\u53f3\u4fa7");
    model.component("comp1").variable("var6").selection().geom("geom1", 2);
    model.component("comp1").variable("var6").selection().set(8, 9, 10);
    model.component("comp1").variable("var6").set("s1", "(110[mm]-R)/(110[mm]-65[mm])");

    model.component("comp1").common().create("pres1", "PrescribedDeformation");
    model.component("comp1").common("pres1").selection().all();
    model.component("comp1").common("pres1").selection().set(3, 4, 5, 8, 9, 10);
    model.component("comp1").common("pres1")
         .set("prescribedDeformation", new String[]{"0", "0", "(u-18[mm])*s1*s2"});

    model.study("std1").feature("time").set("tlist", "range(0,0.01,1.7)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.001);

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "u");
    model.component("comp1").probe("var1").set("descr", "\u94dd\u677f\u4f4d\u7f6e");
    model.component("comp1").probe("var1").set("descractive", true);
    model.component("comp1").probe("var1").set("descr", "\u94dd\u677f\u4f4d\u79fb");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").set("expr", "mf.Forcez_0");
    model.component("comp1").probe("var2").set("descractive", true);
    model.component("comp1").probe("var2").set("table", "new");
    model.component("comp1").probe("var2").set("window", "new");

    model.study("std1").showAutoSequences("all");

    model.result().dataset("dset1").set("frametype", "material");
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(3, 4, 5, 8, 9, 10);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").set("frametype", "material");
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("elemcolor", "none");
    model.result("pg1").feature("mesh1").set("wireframecolor", "white");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "s1*s2");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol1").run("v1");

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().dataset("dset1").set("frametype", "spatial");
    model.result("pg1").run();
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "pres1.dz");

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 17, 0);
    model.result("pg1").run();
    model.result("pg3").set("window", "window2");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u78c1\u529b z \u5206\u91cf");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u7535\u78c1\u529b z \u5206\u91cf (N)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "");
    model.result("pg3").set("window", "window2");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg3").run();
    model.result().dataset().duplicate("dset3", "dset1");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().set(2, 3, 4, 5, 7, 8, 9, 10, 12);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset3");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").run();
    model.result("pg4").create("con1", "Contour");
    model.result("pg4").feature("con1").set("expr", "mf.Aphi");
    model.result("pg4").feature("con1").set("descr", "\u78c1\u77e2\u52bf\uff0cphi \u5206\u91cf");
    model.result("pg4").feature("con1").set("expr", "mf.Aphi*r");
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("color", "white");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "0.01s \u65f6\u7684\u78c1\u901a\u5bc6\u5ea6\u6a21 (T) \u548c\u6d41\u7ebf");
    model.result("pg4").set("paramindicator", "");
    model.result("pg4").run();
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("\u5b9e\u9a8c\u6570\u636e");
    model.result().table("tbl3").importData("electrodynamic_levitation_device_data.txt");
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").label("\u94dd\u677f\u52a8\u529b\u5b66\u6bd4\u8f83");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u94dd\u677f\u8f74\u5411\u4f4d\u79fb (mm)");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u8ba1\u7b97\u6570\u636e", 0);
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").create("tblp2", "Table");
    model.result("pg2").feature("tblp2").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp2").set("linewidth", "preference");
    model.result("pg2").feature("tblp2").set("table", "tbl3");
    model.result("pg2").feature("tblp2").set("linemarker", "cycle");
    model.result("pg2").feature("tblp2").set("linestyle", "none");
    model.result("pg2").feature("tblp2").set("legend", true);
    model.result("pg2").feature("tblp2").set("legendmethod", "manual");
    model.result("pg2").feature("tblp2").setIndex("legends", "\u5b9e\u9a8c\u6570\u636e", 0);
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result().dataset().duplicate("dset4", "dset3");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(4, 7, 12);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset4");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 240);
    model.result().dataset("rev1").set("startangle", 0);
    model.result().dataset("rev1").set("revangle", 360);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", "mf.Jphi");
    model.result("pg5").feature("vol1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\uff0cphi \u5206\u91cf");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").run();
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
    model.result().export("anim1").set("plotgroup", "pg5");
    model.result().export("anim1").set("looplevelinput", "manual");
    model.result().export("anim1")
         .set("looplevel", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121});
    model.result().export("anim1").set("maxframes", 50);
    model.result("pg5").run();

    model.title("\u7535\u52a8\u60ac\u6d6e\u88c5\u7f6e");

    model
         .description("\u672c\u6a21\u578b\u63d0\u4f9b\u201c\u6d4b\u8bd5\u7535\u78c1\u5206\u6790\u6cd5\u201d(TEAM) \u7814\u8ba8\u4f1a\u95ee\u9898 28\u201c\u7535\u52a8\u60ac\u6d6e\u88c5\u7f6e\u201d\u7684\u89e3\u3002\u8fd9\u662f\u4e00\u4e2a\u6d89\u53ca\u7535\u78c1\u548c\u521a\u4f53\u52a8\u529b\u5b66\u52a8\u6001\u8026\u5408\u7684\u57fa\u51c6\u95ee\u9898\u3002\u5c06\u5bfc\u7535\u76d8\u653e\u7f6e\u5728\u4e24\u4e2a\u540c\u5fc3\u7ebf\u5708\u4e0a\u65b9\uff0c\u4e14\u7ebf\u5708\u643a\u5e26\u65b9\u5411\u76f8\u53cd\u7684\u65f6\u53d8\u7535\u6d41\u65f6\uff0c\u611f\u5e94\u6da1\u6d41\u4f1a\u4ea7\u751f\u7535\u52a8\u60ac\u6d6e\u529b\u3002\u672c\u4f8b\u6c42\u89e3\u60ac\u6d6e\u76d8\u7684\u52a8\u529b\u5b66\u95ee\u9898\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u6587\u732e\u4e2d\u7684\u6d4b\u91cf\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("electrodynamic_levitation_device.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
