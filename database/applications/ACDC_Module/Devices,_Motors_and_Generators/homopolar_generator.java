/*
 * homopolar_generator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class homopolar_generator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mef", "ElectricInductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mef", true);

    model.param().set("Bz0", "1[Wb/m^2]");
    model.param().descr("Bz0", "\u6052\u5b9a\u78c1\u901a\u5bc6\u5ea6");
    model.param().set("RPM", "1200[rpm]");
    model.param().descr("RPM", "\u5706\u76d8\u901f\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 100);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 30);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{0, 0, -15});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 15);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 30);
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new int[]{0, 0, 15});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").feature().duplicate("cyl4", "cyl3");
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 35);
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{130, 20});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{-130, -10});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", 60, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new int[]{13, 20});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new int[]{-130, -10});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1.r1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 10, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl3");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp1.r2");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", -50, 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("ext2", 6);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("size", new int[]{20, 30});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("pos", new int[]{-10, -5});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp2");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 30, 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("ext3");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("cyl1");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("cyl5", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl5").set("r", 300);
    model.component("comp1").geom("geom1").feature("cyl5").set("h", 400);
    model.component("comp1").geom("geom1").feature("cyl5").set("pos", new int[]{0, 0, -200});
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", false);
    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(1, 4);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1, 2, 3, 4, 38, 43);
    model.component("comp1").selection("sel1").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(5, 7);
    model.component("comp1").selection("sel2").label("\u65cb\u8f6c\u5706\u76d8");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(2, 3, 4, 5, 6, 7);
    model.component("comp1").selection("sel3").label("\u94dc\u57df");

    model.component("comp1").physics("mef").create("alcs1", "ElectromagneticModelSolid", 3);
    model.component("comp1").physics("mef").feature("alcs1").selection().set(2, 3, 4, 5, 6, 7);
    model.component("comp1").physics("mef").create("vlt1", "Velocity", 3);
    model.component("comp1").physics("mef").feature("vlt1").selection().named("sel2");
    model.component("comp1").physics("mef").feature("vlt1").set("v", new String[]{"-2*pi*RPM*y", "2*pi*RPM*x", "0"});
    model.component("comp1").physics("mef").create("mp1", "MagneticPotential", 2);
    model.component("comp1").physics("mef").feature("mp1").selection().named("sel1");
    model.component("comp1").physics("mef").feature("mp1").set("A0", new String[]{"0", "Bz0*x", "0"});

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
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("sel3");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 7);
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 10);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().named("sel3");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"mef.Jx", "mef.Jy", "mef.Jz"});
    model.result("pg1").feature("str1").set("descr", "\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg1").feature("str1").set("selnumber", 50);
    model.result("pg1").feature("str1").selection().set(34);
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("radiusexpr", "log10(mef.normJ)-5");
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").feature("str1").feature("col1").set("expr", "mef.normJ");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u4e09\u7ef4\u7535\u6d41\u56fe");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", "mef.normB");
    model.result("pg2").feature("vol1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg2").feature("vol1").label("\u603b B");
    model.result("pg2").run();
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("data", "dset1");
    model.result("pg2").feature("str1").setIndex("expr", "mef.Bz-Bz0", 2);
    model.result("pg2").feature("str1").set("selnumber", 100);
    model.result("pg2").feature("str1").selection().set(24);
    model.result("pg2").run();
    model.result("pg2").feature("str1").label("\u611f\u5e94 B");
    model.result("pg2").run();
    model.result("pg2").label("\u78c1\u901a\u5bc6\u5ea6 (B)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1").set("expr", new String[]{"mef.Jx", "mef.Jy", "mef.Jz"});
    model.result("pg3").feature("arwv1").set("descr", "\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg3").feature("arwv1").set("xnumber", 40);
    model.result("pg3").feature("arwv1").set("ynumber", 40);
    model.result("pg3").feature("arwv1").set("arrowzmethod", "coord");
    model.result("pg3").feature("arwv1").set("zcoord", 0);
    model.result("pg3").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("arwv1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("arwv2", "arwv1");
    model.result("pg3").run();
    model.result("pg3").feature("arwv2").set("expr", new String[]{"mef.Jx", "mef.Jy", "mef.Jz"});
    model.result("pg3").feature("arwv2").set("descr", "\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg3").feature("arwv2").set("xnumber", 80);
    model.result("pg3").feature("arwv2").set("ynumber", 1);
    model.result("pg3").feature("arwv2").set("arrowzmethod", "number");
    model.result("pg3").feature("arwv2").set("znumber", 20);
    model.result("pg3").feature("arwv2").set("inheritplot", "arwv1");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "mef.Qh");
    model.result("pg4").feature("vol1").set("descr", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1");
    model.result("pg4").feature("vol1").set("colortable", "GrayBody");
    model.result("pg4").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").selection().set(27);
    model.result().numerical("int1").set("expr", new String[]{"mef.normJ"});
    model.result().numerical("int1").set("descr", new String[]{"\u7535\u6d41\u5bc6\u5ea6\u6a21"});
    model.result().numerical("int1").set("unit", new String[]{"A"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();

    model.title("\u5355\u6781\u53d1\u7535\u673a");

    model
         .description("\u4e00\u4e2a\u5355\u6781\u53d1\u7535\u673a\u7531\u4e00\u4e2a\u653e\u7f6e\u5728\u5782\u76f4\u4e8e\u65cb\u8f6c\u5e73\u9762\u7684\u5747\u5300\u78c1\u573a\u4e2d\u7684\u5bfc\u7535\u65cb\u8f6c\u5706\u76d8\u7ec4\u6210\u3002\u5bfc\u4f53\u5728\u9759\u78c1\u573a\u4e2d\u7684\u8fd0\u52a8\u5728\u5706\u76d8\u4e2d\u611f\u5e94\u51fa\u6d1b\u4f26\u5179\u7535\u6d41\uff0c\u901a\u8fc7\u4e00\u4e2a\u56fa\u5b9a\u5bfc\u4f53\u5c06\u5706\u76d8\u7684\u5916\u7f18\u8fde\u63a5\u5230\u4e2d\u5fc3\uff0c\u53ef\u4ee5\u4ea7\u751f\u5927\u7535\u6d41\u3002\u672c\u4f8b\u6a21\u62df\u7535\u6d41\u901a\u8fc7\u94dc\u5bfc\u4f53\u548c\u65cb\u8f6c\u5706\u76d8\u7684\u6d41\u52a8\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("homopolar_generator.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
