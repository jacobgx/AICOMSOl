/*
 * three_omega_method_for_estimating_thermal_conductivity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:31 by COMSOL 6.3.0.290. */
public class three_omega_method_for_estimating_thermal_conductivity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Thermal_Processing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "ec");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ec", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/emh1", true);

    model.param().set("f", "1[Hz]");
    model.param().set("L", "3.5[mm]");
    model.param().set("w", "0.1[mm]");
    model.param().set("d", "0.3[mm]");
    model.param().set("th", "0.3[um]");
    model.param().set("omega", "2*pi*f");
    model.param().set("N", "4");
    model.param().set("rho_nano", "3100[kg/m^3]");
    model.param().set("Cp_nano", "820[J/kg/K]");
    model.param().set("k_nano", "1.11[W/m/K]");
    model.param().set("beta", "0.0039[1/K]");
    model.param().set("rho_ref", "1.72e-8[ohm*m]");
    model.param().set("I0", "60[mA]");
    model.param().set("J0", "I0/(w*th)");
    model.param().set("R0", "rho_ref*L/(w*th)");
    model.param().set("V0", "I0*R0");
    model.param().set("Prms", "I0^2*R0/2");
    model.param().set("T_ref", "298 [K]");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"w/2", "L", "th"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"3*w", "L", "d"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "-d"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("layer", "w/2", 0);
    model.component("comp1").geom("geom1").feature("blk2").set("layerright", true);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().set(8);

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().set(1, 4, 5);

    model.component("comp1").common().create("ampr1", "AmbientProperties");
    model.component("comp1").common("ampr1").set("T_amb", "T_ref");

    model.component("comp1").physics("ec").selection().set(3);
    model.component("comp1").physics("ec").feature("cucn1").set("ConstitutiveRelationJcE", "LinearizedResistivity");
    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(13);
    model.component("comp1").physics("ec").create("ncd1", "NormalCurrentDensity", 2);
    model.component("comp1").physics("ec").feature("ncd1").selection().set(8);
    model.component("comp1").physics("ec").feature("ncd1").set("nJ", "J0*cos(omega*t)");
    model.component("comp1").physics("ec").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("ec").feature("symp1").selection().set(7);
    model.component("comp1").physics("ht").feature("init1").set("Tinit_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().set(3);
    model.component("comp1").physics("ht").feature("hs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("hs1").set("P0", "-Prms/2");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(3, 18);
    model.component("comp1").physics("ht").feature("temp1").set("T0_src", "root.comp1.ampr1.T_amb");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().set(1, 4, 7);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(1, 2, 4, 5);
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k_nano"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_nano"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp_nano"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat2").selection().set(3);
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", new String[]{"rho_ref"});
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", new String[]{"beta"});
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", new String[]{"T_ref"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(9, 15, 22);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(9, 17, 32, 37);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(8, 20, 21, 24, 31, 42);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(2, 5);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemcount", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis3").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "f", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").feature("param").setIndex("pname", "f", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10^{range(-6,1,4)}", 0);
    model.study("std1").feature("time").set("tlist", "range(0,0.03,N)/f");
    model.study("std1").create("tffft", "TimeToFreqFFT");
    model.study("std1").feature("tffft").set("fftstarttime", "2/f");
    model.study("std1").feature("tffft").set("fftendtime", "N/f");
    model.study("std1").feature("tffft").set("fftmaxfreq", "5*f");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").setIndex("looplevel", 11, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "Dipole");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (ec)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").setIndex("looplevel", 11, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "ec.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "ec.CPz");
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
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").setIndex("looplevel", 11, 1);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gevs1", "EvalGlobalSweep");
    model.result().evaluationGroup("eg1").feature("gevs1").setIndex("pname", "f0", 0);
    model.result().evaluationGroup("eg1").feature("gevs1").setIndex("plistarr", "10^{range(-6,1,4)}", 0);
    model.result().evaluationGroup("eg1").feature("gevs1")
         .setIndex("expr", "withsol('sol3',real(aveop1(V)),setval(f,f0),setval(freq,3*f0))", 0);
    model.result().evaluationGroup("eg1").feature("gevs1")
         .setIndex("descr", "Voltage amplitude at 3f frequency (V)", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg4").feature("tblp1").set("evaluationgroup", "eg1");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("xlog", true);
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").run();
    model.result("pg4").feature("tblp1").set("linewidth", 2);
    model.result("pg4").feature("tblp1").set("linemarker", "point");
    model.result("pg4").run();
    model.result().param().set("f1", "1e-4[Hz]");
    model.result().param().descr("f1", "First frequency value from log-linear range");
    model.result().param().set("f2", "1[Hz]");
    model.result().param().descr("f2", "Second frequency value from log-linear range");
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg2").set("includeparameters", false);
    model.result().evaluationGroup("eg2").set("transpose", true);
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "k_nano", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "Exact value", 0);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("expr", "-Prms*V0*beta/4/pi/L*log(f2/f1)/(withsol('sol3',real(aveop1(V)),setval(f,f2),setval(freq,3*f2))-withsol('sol3',real(aveop1(V)),setval(f,f1),setval(freq,3*f1)))", 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "3\u03c9 method", 1);
    model.result().evaluationGroup("eg2").run();
    model.result("pg3").run();

    model
         .title("\u4f7f\u7528 3-\u03c9 \u65b9\u6cd5\u8ba1\u7b97\u7eb3\u7c73\u7ed3\u6784\u6750\u6599\u7684\u70ed\u5bfc\u7387");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a 3-\u03c9 \u65b9\u6cd5\u7684\u57fa\u672c\u539f\u7406\u3002\u901a\u8fc7\u5728\u56fa\u4f53\u6837\u54c1\u8868\u9762\u6c89\u79ef\u7684\u8584\u94dc\u5e26\u5bf9\u5176\u8fdb\u884c\u52a0\u70ed\uff0c\u8fdb\u800c\u6c42\u89e3\u6837\u672c\u4e2d\u7684\u8026\u5408\u7535\u78c1\u70ed\u95ee\u9898\u3002\u901a\u8fc7\u5206\u6790\u7535\u538b\u7b2c\u4e09\u8c10\u6ce2 (3-\u03c9) \u7684\u9891\u7387\u4f9d\u8d56\u6027\uff0c\u6765\u786e\u5b9a\u5bf9\u6570\u7ebf\u6027\u533a\u57df\u3002\u636e\u6b64\uff0c\u53ef\u4ee5\u8ba1\u7b97\u6837\u54c1\u7684\u70ed\u5bfc\u7387\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u7cbe\u786e\u503c\u8fdb\u884c\u6bd4\u8f83\uff0c\u4ee5\u9a8c\u8bc1\u6a21\u578b\u7684\u51c6\u786e\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();

    model.label("three_omega_method_for_estimating_thermal_conductivity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
