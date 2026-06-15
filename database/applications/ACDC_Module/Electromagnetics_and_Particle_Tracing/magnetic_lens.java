/*
 * magnetic_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class magnetic_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);

    model.param().set("Ic", "0.32[A]");
    model.param().descr("Ic", "\u7ebf\u5708\u7535\u6d41");
    model.param().set("Nc", "1000");
    model.param().descr("Nc", "\u7ebf\u5708\u531d\u6570");

    model.component("comp1").geom("geom1").insertFile("magnetic_lens_geom_sequence.mph", "geom1");
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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("Perfect vacuum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "0[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").selection().set(1);

    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").selection().set(4);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("CoilType", "Circular");
    model.component("comp1").physics("mf").feature("coil1").set("N", "Nc");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "Ic");
    model.component("comp1").physics("mf").feature("coil1").feature("cre1").selection().set(22, 23, 57, 82);

    model.component("comp1").mesh("mesh1").create("sca1", "Scale");
    model.component("comp1").mesh("mesh1").feature("sca1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("sca1").selection().set(2, 3, 4, 5);
    model.component("comp1").mesh("mesh1").feature("sca1").set("scale", 0.5);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(30);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

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
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("zcoord", 0);
    model.result("pg1").run();
    model.result("pg1").feature("strmsl1").set("zcoord", 0);
    model.result("pg1").run();

    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/cpt", false);
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mf", false);
    model.study("std2").feature("time").setSolveFor("/physics/cpt", true);

    model.component("comp1").physics("cpt").selection().set(1);
    model.component("comp1").physics("cpt").feature("pp1").set("ParticleSpecies", "Electron");
    model.component("comp1").physics("cpt").create("mf1", "MagneticForce", 3);
    model.component("comp1").physics("cpt").feature("mf1").selection().all();
    model.component("comp1").physics("cpt").feature("mf1").set("B_src", "root.comp1.mf.Bx");
    model.component("comp1").physics("cpt").create("pbeam1", "ParticleBeam", 2);
    model.component("comp1").physics("cpt").feature("pbeam1").selection().set(30);
    model.component("comp1").physics("cpt").feature("pbeam1").setIndex("N", 10000, 0);
    model.component("comp1").physics("cpt").feature("pbeam1").set("e1rms", "0.1[um]");
    model.component("comp1").physics("cpt").feature("pbeam1").set("El", "0.5[keV]");

    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").feature("time").set("tlist", "range(0,1.0204081632653062e-10,5.0e-9)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "part1");
    model.result("pg2").setIndex("looplevel", 50, 0);
    model.result("pg2").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg2").create("traj1", "ParticleTrajectories");
    model.result("pg2").feature("traj1").set("pointtype", "point");
    model.result("pg2").feature("traj1").set("linetype", "none");
    model.result("pg2").feature("traj1").create("col1", "Color");
    model.result("pg2").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "part1");
    model.result("pg3").setIndex("looplevel", 50, 0);
    model.result("pg3").label("\u5e73\u5747\u675f\u6d41\u4f4d\u7f6e (cpt)");
    model.result("pg3").create("pttraj1", "PointTrajectories");
    model.result("pg3").feature("pttraj1").set("plotdata", "global");
    model.result("pg3").feature("pttraj1").set("globalexpr", new String[]{"cpt.qavx", "cpt.qavy", "cpt.qavz"});
    model.result("pg3").feature("pttraj1").create("col1", "Color");
    model.result("pg3").feature("pttraj1").feature("col1").set("expr", "cpt.e1hrms");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("traj1").set("linetype", "line");
    model.result("pg2").feature("traj1").set("pointtype", "none");
    model.result("pg2").run();
    model.result("pg2").feature("traj1").feature("col1").set("expr", "sqrt(cpt.Ftx^2+cpt.Fty^2+cpt.Ftz^2)");
    model.result("pg2").feature("traj1").feature("col1").set("colortable", "Plasma");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").label("\u5e73\u5747\u675f\u6d41\u4f4d\u7f6e\u548c\u8d85\u53d1\u5c04\u5ea6");
    model.result("pg3").run();
    model.result("pg3").feature("pttraj1").set("linetype", "tube");
    model.result("pg3").feature("pttraj1").set("radiusexpr", "cpt.e1hrms");
    model.result("pg3").feature("pttraj1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("pttraj1").set("tuberadiusscale", "4E10");
    model.result("pg3").feature("pttraj1").set("interpolation", "uniform");
    model.result("pg3").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("quickz", -6);
    model.result().dataset("cpl1").set("data", "part1");
    model.result().dataset().duplicate("cpl2", "cpl1");
    model.result().dataset("cpl2").set("quickz", 7);
    model.result().dataset().duplicate("cpl3", "cpl2");
    model.result().dataset("cpl3").set("quickz", 34);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u5e9e\u52a0\u83b1\u56fe");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u5e9e\u52a0\u83b1\u56fe");
    model.result("pg4").create("poma1", "PoincareMap");
    model.result("pg4").feature("poma1").set("data", "cpl3");
    model.result("pg4").feature("poma1").set("color", "black");
    model.result("pg4").feature("poma1").set("solutionparams", "parent");
    model.result("pg4").feature().duplicate("poma2", "poma1");
    model.result("pg4").run();
    model.result("pg4").feature("poma2").set("data", "cpl1");
    model.result("pg4").feature("poma2").set("color", "red");
    model.result("pg4").feature().duplicate("poma3", "poma2");
    model.result("pg4").run();
    model.result("pg4").feature("poma3").set("data", "cpl2");
    model.result("pg4").feature("poma3").set("color", "blue");
    model.result("pg4").run();

    model.title("\u78c1\u900f\u955c");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u5e26\u7535\u7c92\u5b50\u8ffd\u8e2a\u201d\u63a5\u53e3\u8ba1\u7b97\u7a7a\u95f4\u53d8\u5316\u78c1\u573a\u4e2d\u7684\u7535\u5b50\u8f68\u8ff9\u3002\u901a\u8fc7\u8ba1\u7b97\u6cbf\u675f\u6d41\u8f68\u8ff9\u4e0a\u7684\u53d1\u5c04\u5ea6\uff0c\u8fdb\u4e00\u6b65\u5206\u6790\u4e86\u7535\u5b50\u675f\u4e0a\u78c1\u573a\u7684\u805a\u7126\u6548\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("magnetic_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
