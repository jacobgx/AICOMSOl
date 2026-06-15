/*
 * planar_diode.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:51 by COMSOL 6.3.0.290. */
public class planar_diode {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");
    model.component("comp1").physics("cpt").prop("ParticleReleaseSpecification")
         .set("ParticleReleaseSpecification", "SpecifyCurrent");
    model.component("comp1").physics("cpt").prop("RelativisticCorrection").set("RelativisticCorrection", "0");
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce");
    model.component("comp1").physics("cpt").feature("ef1").selection().all();
    model.component("comp1").physics("cpt").feature("ef1").set("SpecifyForceUsing", new String[]{"ElectricField"});

    model.component("comp1").multiphysics().create("epfi1", "ElectricParticleFieldInteraction", 3);
    model.component("comp1").multiphysics("epfi1").set("ChargeDensitySource_physics", "cpt");
    model.component("comp1").multiphysics("epfi1").set("ChargeDensityDestination_physics", "es");

    model.study().create("std1");
    model.study("std1").create("bcpt", "BidirectionallyCoupledParticleTracing");
    model.study("std1").feature("bcpt").setSolveFor("/physics/es", true);
    model.study("std1").feature("bcpt").setSolveFor("/physics/cpt", true);
    model.study("std1").feature("bcpt").setSolveFor("/multiphysics/epfi1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rc", "10[mm]", "\u7535\u6781\u4fa7\u957f\u5ea6");
    model.param().set("d", "1[mm]", "\u7535\u6781\u95f4\u8ddd");
    model.param().set("Va", "100[V]", "\u9633\u6781\u7535\u538b");
    model.param().set("Tc", "2500[K]", "\u9634\u6781\u6e29\u5ea6");
    model.param().set("Phim", "4.5[V]", "\u9634\u6781\u529f\u51fd\u6570");
    model.param().set("A0", "110[A/(K*cm)^2]", "\u6709\u6548 Richardson \u5e38\u6570");
    model.param().set("N", "100", "\u7c92\u5b50\u4f4d\u7f6e\u6570");
    model.param()
         .set("Nvel", "50", "\u6bcf\u4e2a\u91ca\u653e\u4f4d\u7f6e\u7684\u901f\u5ea6\u7a7a\u95f4\u7c92\u5b50\u6570");
    model.param().set("Jaref", "-0.25356[A/cm^2]", "\u9633\u6781\u7535\u6d41\u5bc6\u5ea6\uff0c\u53c2\u8003\u503c");
    model.param().set("Iref", "Jaref*rc^2", "\u9633\u6781\u7535\u6d41\uff0c\u53c2\u8003\u503c");
    model.param().set("yref", "11.412[um]", "\u6700\u4f4e\u7535\u4f4d\u7684\u4f4d\u7f6e\uff0c\u53c2\u8003\u503c");
    model.param().set("Vref", "-179.37[mV]", "\u6700\u4f4e\u7535\u4f4d\uff0c\u53c2\u8003\u503c");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"rc", "d", "rc"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-rc/2", "0", "-rc/2"});
    model.component("comp1").geom("geom1").feature("blk1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("blk1").set("layerfront", true);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "d/20", 0);

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "planar_diode_reference.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("funcname", "reference");
    model.component("comp1").func("int1").setIndex("argunit", "um", 0);
    model.component("comp1").func("int1").setIndex("fununit", "mV", 0);

    model.component("comp1").cpl().create("minop1", "Minimum");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("minop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("minop1").selection().set(4);

    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(2);
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").selection().set(9);
    model.component("comp1").physics("es").feature("pot1").set("V0", "Va");
    model.component("comp1").physics("cpt").create("te1", "ThermionicEmission", 2);
    model.component("comp1").physics("cpt").feature("te1").selection().set(2);
    model.component("comp1").physics("cpt").feature("te1").set("T", "Tc");
    model.component("comp1").physics("cpt").feature("te1").set("Phim", "Phim");
    model.component("comp1").physics("cpt").feature("te1").set("Ath", "A0");
    model.component("comp1").physics("cpt").feature("te1").setIndex("N", "N", 0);
    model.component("comp1").physics("cpt").feature("te1").setIndex("Nvel", "Nvel", 0);
    model.component("comp1").physics("cpt").feature("te1").set("WeightingOfMacroparticles", "UniformSpeedIntervals");
    model.component("comp1").physics("cpt").feature("te1").set("nw", 20);
    model.component("comp1").physics("cpt").feature("pp1").set("ParticleSpecies", "Electron");
    model.component("comp1").physics("cpt").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("cpt").feature("sym1").selection().set(1, 3, 4, 5, 7, 8, 10, 11);
    model.component("comp1").physics("cpt").create("pcnt1", "ParticleCounter", 2);
    model.component("comp1").physics("cpt").feature("pcnt1").selection().set(9);
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("cpt").feature("ef1").set("UsePPR", true);

    model.component("comp1").multiphysics("epfi1").set("UseCumulativeSpaceChargeDensity", true);
    model.component("comp1").multiphysics("epfi1").set("beta", 0);
    model.component("comp1").multiphysics("epfi1").set("WeightsForSubsequentIterations", "ArithmeticSequence");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 3, 5, 14);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 80);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("bcpt").set("tunit", "\u00b5s");
    model.study("std1").feature("bcpt").set("tlist", "0 0.1");
    model.study("std1").feature("bcpt").set("iter", 50);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Dipole");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "es.CPz");
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
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "es.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
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
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol1");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "part1");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg1").run();
    model.result().dataset().create("edg1", "Edge3D");
    model.result().dataset("edg1").selection().set(4);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u52bf\u6700\u5c0f\u503c");
    model.result("pg4").set("data", "edg1");
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7535\u52bf (mV)");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("unit", "mV");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "y");
    model.result("pg4").feature("lngr1").set("xdataunit", "\u00b5m");
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").setIndex("legends", "\u7c92\u5b50\u8ffd\u8e2a", 0);
    model.result("pg4").run();
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr2").set("linewidth", "preference");
    model.result("pg4").feature("lngr2").set("expr", "reference(y)");
    model.result("pg4").feature("lngr2").set("unit", "mV");
    model.result("pg4").feature("lngr2").set("xdata", "expr");
    model.result("pg4").feature("lngr2").set("xdataexpr", "y");
    model.result("pg4").feature("lngr2").set("xdataunit", "\u00b5m");
    model.result("pg4").feature("lngr2").set("linecolor", "black");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").setIndex("legends", "\u53c2\u8003\u503c", 0);
    model.result("pg4").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "part1");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").setIndex("expr", "cpt.pcnt1.It", 0);
    model.result().numerical("gev1")
         .setIndex("descr", "\u4f20\u8f93\u7684\u7535\u6d41\uff0c\u7c92\u5b50\u8ba1\u6570\u5668 1", 0);
    model.result().numerical("gev1").setIndex("expr", "minop1(V,y)", 1);
    model.result().numerical("gev1").setIndex("unit", "um", 1);
    model.result().numerical("gev1").setIndex("descr", "\u7535\u52bf\u6700\u5c0f\u503c\u7684\u4f4d\u7f6e", 1);
    model.result().numerical("gev1").setIndex("expr", "minop1(V)", 2);
    model.result().numerical("gev1").setIndex("unit", "mV", 2);
    model.result().numerical("gev1").setIndex("descr", "\u7535\u52bf\u6700\u5c0f\u503c", 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "part1");
    model.result().numerical("gev2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev2").setIndex("expr", "abs((Iref-cpt.pcnt1.It)/Iref)*100", 0);
    model.result().numerical("gev2")
         .setIndex("descr", "\u4f20\u8f93\u7535\u6d41\u7684\u76f8\u5bf9\u8bef\u5dee (%)", 0);
    model.result().numerical("gev2").setIndex("expr", "abs((yref-minop1(V,y))/yref)*100", 1);
    model.result().numerical("gev2")
         .setIndex("descr", "\u7535\u52bf\u6700\u5c0f\u503c\u4f4d\u7f6e\u7684\u76f8\u5bf9\u8bef\u5dee (%)", 1);
    model.result().numerical("gev2").setIndex("expr", "abs((Vref-minop1(V))/Vref)*100", 2);
    model.result().numerical("gev2")
         .setIndex("descr", "\u7535\u52bf\u6700\u5c0f\u503c\u7684\u76f8\u5bf9\u8bef\u5dee (%)", 2);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();

    model.title("\u5e73\u9762\u4e8c\u6781\u7ba1\u4e2d\u7684\u70ed\u7535\u5b50\u53d1\u5c04");

    model
         .description("\u5f53\u7535\u5b50\u4ece\u5e73\u9762\u5e73\u884c\u771f\u7a7a\u4e8c\u6781\u7ba1\u7684\u70ed\u9634\u6781\u53d1\u5c04\u65f6\uff0c\u4f1a\u5bfc\u81f4\u4e8c\u6781\u7ba1\u4e2d\u7684\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6\u589e\u52a0\uff0c\u8fdb\u800c\u5f71\u54cd\u7535\u52bf\u7684\u5206\u5e03\u3002\u5982\u679c\u9634\u6781\u4e0e\u9633\u6781\u4e4b\u95f4\u7684\u7535\u52bf\u5dee\u4e0d\u591f\u5927\uff0c\u4f1a\u5728\u5b83\u4eec\u4e4b\u95f4\u5f62\u6210\u6700\u4f4e\u7535\u52bf\uff0c\u5c06\u80fd\u91cf\u4e0d\u8db3\u7684\u7535\u5b50\u6392\u65a5\u56de\u9634\u6781\u3002\u5728\u8fd9\u79cd\u60c5\u51b5\u4e0b\uff0c\u4e8c\u6781\u7ba1\u88ab\u8ba4\u4e3a\u662f\u5904\u4e8e\u7a7a\u95f4\u7535\u8377\u9650\u5236\u72b6\u6001\u3002\n\n\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u4f7f\u7528\u4e13\u7528\u7684\u70ed\u7535\u5b50\u53d1\u5c04 \u7279\u5f81\uff0c\u4ece\u5177\u6709\u6307\u5b9a\u6e29\u5ea6\u548c\u529f\u51fd\u6570\u7684\u9634\u6781\u91ca\u653e\u70ed\u7535\u5b50\uff0c\u5e76\u4f7f\u7528\u4e13\u95e8\u7684\u7535\u7c92\u5b50-\u573a\u76f8\u4e92\u4f5c\u7528 \u591a\u7269\u7406\u573a\u8026\u5408\u548c\u53cc\u5411\u8026\u5408\u7c92\u5b50\u8ffd\u8e2a \u7814\u7a76\u6b65\u9aa4\uff0c\u5c06\u7535\u5b50\u8f68\u8ff9\u4e0e\u4e8c\u6781\u7ba1\u4e2d\u7684\u7535\u52bf\u8ba1\u7b97\u8fdb\u884c\u53cc\u5411\u8026\u5408\u3002\u7535\u52bf\u5206\u5e03\u548c\u9633\u6781\u7535\u6d41\u4e0e\u89e3\u6790 Langmuir-Fry \u6a21\u578b\u7684\u7ed3\u679c\u9ad8\u5ea6\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("planar_diode.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
