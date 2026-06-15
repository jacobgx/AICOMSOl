/*
 * power_inductor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class power_inductor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Inductive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mef", "ElectricInductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mef", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "power_inductor.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.2, 0.15, 0.12});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-0.1, -0.08, -0.04});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run();

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
    model.component("comp1").material("mat1").selection().set(3);

    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_electricpotential", 1);
    model.component("comp1").physics("mef").feature("mi1").create("ein1", "ElectricInsulation", 2);
    model.component("comp1").physics("mef").feature("mi1").feature("ein1").selection().set(1, 2, 3, 4, 5, 79);
    model.component("comp1").physics("mef").feature("mi1").create("term1", "Terminal", 2);
    model.component("comp1").physics("mef").feature("mi1").feature("term1").selection().set(17);
    model.component("comp1").physics("mef").feature("mi1").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("mef").create("alcs1", "ElectromagneticModelSolid", 3);
    model.component("comp1").physics("mef").feature("alcs1").selection().set(3);
    model.component("comp1").physics("mef").create("alcs2", "ElectromagneticModelSolid", 3);
    model.component("comp1").physics("mef").feature("alcs2").selection().set(2);
    model.component("comp1").physics("mef").feature("alcs2").set("ConstitutiveRelationBH", "MagneticLosses");
    model.component("comp1").physics("mef").create("gfa1", "GaugeFixingA", 3);

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u78c1\u82af\u6750\u6599");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("MagneticLosses", "MagneticLosses", "Magnetic_losses");
    model.component("comp1").material("mat2").propertyGroup("MagneticLosses").set("murPrim", new String[]{"1000"});
    model.component("comp1").material("mat2").propertyGroup("MagneticLosses").set("murBis", new String[]{"10"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "5[mm]");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(16, 18, 19, 20, 21, 22, 23, 24, 25, 26, 62, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "0.5[mm]");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "1[kHz]");
    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"mef/gfa1"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol1").feature("s1").feature("i1").set("itrestart", 500);
    model.sol("sol1").feature("s1").feature("i1").set("prefuntype", "right");
    model.sol("sol1").feature("s1").feature("i1").set("rhob", "1e7");
    model.sol("sol1").feature("s1").feature("i1").create("so1", "SOR");

    model.study("std1").setGenPlots(false);
    model.study("std1").label("\u65e0\u89c4\u8303\u56fa\u5b9a\u683c\u5f0f");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/mef", true);
    model.study("std2").feature("freq").set("plist", "1[kHz]");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "dDef");

    model.study("std2").label("\u89c4\u8303\u56fa\u5b9a\u683c\u5f0f");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "real(mef.Y11)", 0);
    model.result().numerical("gev1").setIndex("expr", "real(1/mef.Y11/mef.iomega)", 1);
    model.result().numerical("gev1").setIndex("unit", "uH", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("int1", "IntVolume");
    model.result().numerical("int1").selection().set(2, 3);
    model.result().numerical("int1").setIndex("expr", "2*mef.Qh/1[V^2]", 0);
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").appendResult();
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u52bf\uff0c\u6bd4\u8f83");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("data", "dset1");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().set(3);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("vol2", "vol1");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").set("data", "dset2");
    model.result("pg1").feature("vol2").set("inheritplot", "vol1");
    model.result("pg1").feature("vol2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("vol2").feature("trn1").set("move", new double[]{0.2, 0, 0});

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u573a\uff0c\u6bd4\u8f83");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("expr", "mef.normE");
    model.result("pg2").run();
    model.result("pg2").feature("vol2").set("expr", "mef.normE");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u52bf\uff0c\u89c4\u8303\u56fa\u5b9a\u683c\u5f0f");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection()
         .set(3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u529f\u7387\u7535\u611f\u5668\u7684\u7535\u611f");

    model
         .description("\u529f\u7387\u7535\u611f\u5668\u662f\u8bb8\u591a\u4f4e\u9891\u529f\u7387\u5e94\u7528\u7684\u6838\u5fc3\u90e8\u5206\uff0c\u4f8b\u5982\uff0c\u53ef\u7528\u4e8e\u8ba1\u7b97\u673a\u4e3b\u677f\u548c\u6240\u6709\u5176\u4ed6\u90e8\u4ef6\u7684\u5f00\u5173\u7535\u6e90\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u7ec8\u7aef\u201d\u8fb9\u754c\u6761\u4ef6\u6765\u63d0\u53d6\u7535\u611f\uff1b\u6b64\u5916\uff0c\u8fd8\u6f14\u793a\u5982\u4f55\u5728\u5305\u542b\u548c\u4e0d\u542b\u89c4\u8303\u56fa\u5b9a\u7684\u60c5\u51b5\u4e0b\u6c42\u89e3\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("power_inductor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
