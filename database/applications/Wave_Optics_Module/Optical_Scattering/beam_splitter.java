/*
 * beam_splitter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:40 by COMSOL 6.3.0.290. */
public class beam_splitter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Optical_Scattering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", true);

    model.param().set("lda0", "700[nm]");
    model.param().descr("lda0", "\u771f\u7a7a\u6ce2\u957f");
    model.param().set("lda", "lda0/1.5");
    model.param().descr("lda", "\u6750\u6599\u4e2d\u7684\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("eps_Ag", "-16.5-1.06*i");
    model.param().descr("eps_Ag", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u94f6");
    model.param().set("w0", "5*lda0");
    model.param().descr("w0", "\u5149\u6591\u534a\u5f84");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -10, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -10, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -10, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 10, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 10, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 10, 2, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr1").selection().set(1);
    model.component("comp1").physics("ewfd").feature("sctr1").set("IncidentField", "GaussianBeam");
    model.component("comp1").physics("ewfd").feature("sctr1").set("w0", "w0");
    model.component("comp1").physics("ewfd").feature("sctr1").set("Eg0", new String[]{"0", "0", "1[V/m]"});
    model.component("comp1").physics("ewfd").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr2").selection().set(2, 4, 5);
    model.component("comp1").physics("ewfd").create("trans1", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("ewfd").feature("trans1").selection().set(3);
    model.component("comp1").physics("ewfd").feature("trans1").set("DisplacementFieldModel", "RelativePermittivity");
    model.component("comp1").physics("ewfd").feature("trans1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans1").set("epsilonr", "eps_Ag");
    model.component("comp1").physics("ewfd").feature("trans1").set("murbnd_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans1").set("sigmabnd_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans1").set("d", "13[nm]");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").label("Glass (quartz)");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.99);
    model.component("comp1").material("mat1").set("roughness", 0.02);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2210[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.5", "0", "0", "0", "1.5", "0", "0", "0", "1.5"});

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", "-ewfd.nPoav");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "y");
    model.result("pg2").run();
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr2").set("linewidth", "preference");
    model.result("pg2").feature("lngr2").selection().set(5);
    model.result("pg2").feature("lngr2").set("expr", "ewfd.nPoav");
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "y");
    model.result("pg2").run();
    model.result("pg2").create("lngr3", "LineGraph");
    model.result("pg2").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr3").set("linewidth", "preference");
    model.result("pg2").feature("lngr3").selection().set(4);
    model.result("pg2").feature("lngr3").set("expr", "ewfd.nPoav");
    model.result("pg2").feature("lngr3").set("xdata", "expr");
    model.result("pg2").feature("lngr3").set("xdataexpr", "x");
    model.result("pg2").run();
    model.result("pg2").create("lngr4", "LineGraph");
    model.result("pg2").feature("lngr4").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr4").set("linewidth", "preference");
    model.result("pg2").feature("lngr4").selection().set(3);
    model.result("pg2").feature("lngr4").set("expr", "ewfd.Qsrh");
    model.result("pg2").feature("lngr4").set("xdata", "expr");
    model.result("pg2").feature("lngr4").set("xdataexpr", "x");
    model.result("pg2").run();

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

    model.study("std1").feature("freq").setSolveFor("/physics/ewbe", true);

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("k1", new String[]{"ewbe.k", "0", "0"});
    model.component("comp1").physics("ewbe").prop("WaveVector").set("k2", new String[]{"0", "ewbe.k1x", "0"});
    model.component("comp1").physics("ewbe").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewbe").feature("sctr1").selection().set(1);
    model.component("comp1").physics("ewbe").feature("sctr1").set("IncidentField", "GaussianBeam");
    model.component("comp1").physics("ewbe").feature("sctr1").set("w0", "w0");
    model.component("comp1").physics("ewbe").feature("sctr1").set("Eg0", new String[]{"0", "0", "1[V/m]"});
    model.component("comp1").physics("ewbe").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewbe").feature("sctr2").selection().set(4);
    model.component("comp1").physics("ewbe").create("sctr3", "Scattering", 1);
    model.component("comp1").physics("ewbe").feature("sctr3").selection().set(2, 5);
    model.component("comp1").physics("ewbe").feature("sctr3").set("inputWave", "SecondWave");
    model.component("comp1").physics("ewbe").create("trans1", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("trans1").selection().set(3);
    model.component("comp1").physics("ewbe").feature("trans1").set("DisplacementFieldModel", "RelativePermittivity");
    model.component("comp1").physics("ewbe").feature("trans1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("ewbe").feature("trans1").set("epsilonr", "eps_Ag");
    model.component("comp1").physics("ewbe").feature("trans1").set("murbnd_mat", "userdef");
    model.component("comp1").physics("ewbe").feature("trans1").set("sigmabnd_mat", "userdef");
    model.component("comp1").physics("ewbe").feature("trans1").set("d", "13[nm]");

    model.component("comp1").mesh("mesh1").contribute("physics/ewbe", false);
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("physics/ewfd", false);

    model.component("comp1").physics("ewbe").prop("MeshControl").set("MeshType", "Triangular");
    model.component("comp1").physics("ewbe").prop("MeshControl").set("hMax", "2*lda0");

    model.component("comp1").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("ftplistmethod", "manual");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/ewfd", false);
    model.study("std2").feature("freq").setSolveFor("/physics/ewbe", true);
    model.study("std2").feature("freq").set("plist", "f0");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a\uff0c\u7b2c\u4e00\u4e2a\u6ce2 (ewbe)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u7535\u573a");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "ewbe.normE1");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a\uff0c\u7b2c\u4e8c\u4e2a\u6ce2 (ewbe)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u7535\u573a");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "ewbe.normE2");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u573a (ewbe)");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "ewbe.normE");
    model.result("pg5").feature("surf1").set("resolution", "extrafine");
    model.result("pg5").run();

    model.study("std1").feature("freq").setSolveFor("/physics/ewbe", false);

    model.result("pg5").run();

    model.title("\u5206\u675f\u5668");

    model
         .description("\u5206\u675f\u5668\u7528\u6765\u5c06\u4e00\u675f\u5149\u62c6\u5206\u6210\u4e24\u675f\u3002\u4e00\u79cd\u5206\u675f\u5668\u7684\u5236\u4f5c\u65b9\u6cd5\u662f\u5728\u4e24\u4e2a\u73bb\u7483\u68f1\u955c\u4e2d\u9540\u4e0a\u4e00\u5c42\u8584\u91d1\u5c5e\uff0c\u5c42\u95f4\u7684\u5149\u675f\u6709\u8f7b\u5fae\u7684\u8870\u51cf\uff0c\u7136\u540e\u5206\u6210\u4e24\u675f\u3002\u672c\u4f8b\u4f7f\u7528\u8fc7\u6e21\u8fb9\u754c\u6761\u4ef6\u5bf9\u8584\u91d1\u5c5e\u5c42\u5efa\u6a21\uff0c\u51cf\u5c11\u4e86\u5185\u5b58\u9700\u6c42\u3002\u540c\u65f6\u8fd8\u8ba1\u7b97\u4e86\u91d1\u5c5e\u5c42\u7684\u635f\u8017\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("beam_splitter.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
