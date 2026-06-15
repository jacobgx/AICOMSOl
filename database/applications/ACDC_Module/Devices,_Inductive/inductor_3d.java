/*
 * inductor_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:17 by COMSOL 6.3.0.290. */
public class inductor_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Inductive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "inductor_3d.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 0.2);
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", 0.05, 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(7, 8, 14);
    model.component("comp1").selection("sel1").label("\u7ed5\u7ec4");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(9);
    model.component("comp1").selection("sel2").label("\u95f4\u9699");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(6);
    model.component("comp1").selection("sel3").label("\u78c1\u82af");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").set(1, 2, 3, 4, 10, 11, 12, 13);
    model.component("comp1").selection("sel4").label("\u65e0\u9650\u5143");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").set(1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 13);
    model.component("comp1").selection("sel5").label("\u975e\u5bfc\u4f53");
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").set(5, 6, 9);
    model.component("comp1").selection("sel6").label("\u4e0d\u542b\u65e0\u9650\u5143\u7684\u975e\u5bfc\u4f53");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().named("sel4");
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Spherical");

    model.component("comp1").physics("mf").feature("fsp1").set("sigma_stab_mat", "userdef");
    model.component("comp1").physics("mf").feature("fsp1")
         .set("sigma_stab", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").selection().set(6);
    model.component("comp1").physics("mf").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14);
    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("mf").feature("coil1").selection().named("sel1");
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection().set(58);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").create("cg1", "CoilGround", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("cg1").selection().set(79);

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
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().named("sel3");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1e3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat2").label("\u78c1\u82af");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").feature().move("ccc", 0);
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

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().dataset().duplicate("dset3", "dset1");
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().named("sel1");
    model.result().dataset().duplicate("dset4", "dset1");
    model.result().dataset("dset4").selection().geom("geom1", 3);
    model.result().dataset("dset4").selection().named("sel3");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("|B| \u548c\u7ebf\u5708\u65b9\u5411");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1")
         .set("expr", new String[]{"mf.coil1.eCoilx", "mf.coil1.eCoily", "mf.coil1.eCoilz"});
    model.result("pg2").feature("str1").set("descr", "\u7ebf\u5708\u65b9\u5411");
    model.result("pg2").feature("str1").selection().set(58);
    model.result("pg2").run();
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("data", "dset4");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "mf.RCoil_1", 0);
    model.result().numerical("gev1").setIndex("expr", "mf.LCoil_1", 1);
    model.result().numerical("gev1").setIndex("expr", "mf.VCoil_1/mf.ICoil_1", 2);
    model.result().numerical("gev1").setIndex("descr", "\u7535\u538b\u964d\u5b9a\u4e49", 2);
    model.result().numerical("gev1").setIndex("expr", "2*mf.intWm/mf.ICoil_1^2", 3);
    model.result().numerical("gev1")
         .setIndex("descr", "\u901a\u8fc7\u78c1\u80fd\u5bc6\u5ea6\u8ba1\u7b97\u7684\u7535\u611f", 3);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component("comp1").physics("mf").selection().set(5, 6, 7, 8, 14);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();

    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.study("std1").feature("ccc").setSolveFor("/physics/cir", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cir", true);

    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "CircuitCurrent");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "100[mohm]");
    model.component("comp1").physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp1").physics("cir").feature("IvsU1").set("V_src", "root.comp1.mf.VCoil_1");
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 2, 0, 0);
    model.component("comp1").physics("cir").feature("IvsU1").setIndex("Connections", 0, 1, 0);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "mf.ICoil_1", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/mf", true);
    model.study("std2").feature("freq").setSolveFor("/physics/cir", false);

    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").set(7, 8, 14);
    model.component("comp1").selection("sel7").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel7").set(7, 8, 14);
    model.component("comp1").selection("sel7").label("\u5bfc\u4f53\u8fb9\u754c");

    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als2").selection().named("sel3");
    model.component("comp1").physics("mf").feature("als2").set("ConstitutiveRelationBH", "MagneticLosses");
    model.component("comp1").physics("mf").feature("als2").set("murPrim_mat", "userdef");
    model.component("comp1").physics("mf").feature("als2").set("murBis_mat", "userdef");
    model.component("comp1").physics("mf").feature("als2").set("murPrim", 1200);
    model.component("comp1").physics("mf").feature("als2").set("murBis", 100);

    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat3").label("Copper 1");
    model.component("comp1").material("mat3").set("family", "copper");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat3").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat3").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().named("sel7");

    model.component("comp1").physics("mf").selection().named("sel6");
    model.component("comp1").physics("mf").create("imp1", "Impedance", 2);
    model.component("comp1").physics("mf").feature("imp1").selection().named("sel7");
    model.component("comp1").physics("mf").feature("coil1").active(false);
    model.component("comp1").physics("mf").feature("als1").active(false);
    model.component("comp1").physics("mf").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("mf").feature("lport1").selection().set(59, 60, 61, 62);
    model.component("comp1").physics("mf").feature("lport1").set("PortType", "UserDefined");

    model.component("comp1").view("view1").set("showDirections", false);

    model.component("comp1").physics("mf").feature("lport1").set("hPort", 0.024);
    model.component("comp1").physics("mf").feature("lport1").set("wPort", 0.046);
    model.component("comp1").physics("mf").feature("lport1").set("ahPort", new int[]{1, 0, 0});
    model.component("comp1").physics("mf").feature("lport1").set("TerminalType", "Current");

    model.study("std2").feature("freq").set("punit", "MHz");
    model.study("std2").feature("freq").set("plist", "range(1,0.25,10)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u78c1\u901a\u5bc6\u5ea6 (mf) 1");
    model.result("pg3").set("data", "dset5");
    model.result("pg3").setIndex("looplevel", 37, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "mf.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "mf.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "mf.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "mf.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "mf.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "mf.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg3").run();
    model.result().dataset().duplicate("dset6", "dset5");
    model.result().dataset("dset6").selection().geom("geom1", 2);
    model.result().dataset("dset6").selection().named("sel7");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u8868\u9762\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg4").set("data", "dset6");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "mf.normJs");
    model.result("pg4").feature("surf1").set("descr", "\u8868\u9762\u7535\u6d41\u5bc6\u5ea6\u6a21");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u7ebf\u5708\u963b\u6297");
    model.result("pg5").set("data", "dset5");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").run();
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("expr", new String[]{"mf.Zport_1"});
    model.result("pg5").feature("glob1")
         .set("descr", new String[]{"\u96c6\u603b\u7aef\u53e3\u201c1\u201d\u963b\u6297"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"\u03a9"});
    model.result("pg5").feature("glob1").setIndex("expr", "real(mf.Zport_1)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u963b\u6297\u5b9e\u90e8", 0);
    model.result("pg5").run();
    model.result("pg5").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg5").feature("glob2").setIndex("expr", "imag(mf.Zport_1)", 0);
    model.result("pg5").feature("glob2").setIndex("descr", "\u963b\u6297\u865a\u90e8", 0);
    model.result("pg5").run();
    model.result("pg2").run();

    model.study("std1").feature("stat").setSolveFor("/physics/mf", false);

    model.component("comp1").view("view1").set("showgrid", true);

    model.title("\u7535\u611f\u5668\u4e09\u7ef4\u5efa\u6a21");

    model
         .description("\u7535\u611f\u5668\u662f\u8bb8\u591a\u5e94\u7528\u4e2d\u7684\u91cd\u8981\u5143\u4ef6\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u63d0\u53d6\u7535\u611f\u5668\u4e2d\u7684\u76f4\u6d41\u548c\u4ea4\u6d41\u5c5e\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("inductor_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
