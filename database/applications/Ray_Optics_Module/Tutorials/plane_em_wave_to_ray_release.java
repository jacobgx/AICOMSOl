/*
 * plane_em_wave_to_ray_release.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:24 by COMSOL 6.3.0.290. */
public class plane_em_wave_to_ray_release {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

    model.param().set("W", "1[um]");
    model.param().descr("W", "\u6ce2\u5bfc\u5bbd\u5ea6");
    model.param().set("L", "5[um]");
    model.param().descr("L", "\u6ce2\u5bfc\u957f\u5ea6");
    model.param().set("lam0", "1[um]");
    model.param().descr("lam0", "\u6ce2\u957f");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"W", "W", "L"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ewfd").create("port1", "Port", 2);
    model.component("comp1").physics("ewfd").feature("port1").selection().set(3);
    model.component("comp1").physics("ewfd").feature("port1").set("Pin", "1e-9[W]");
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 2);
    model.component("comp1").physics("ewfd").feature("sctr1").selection().set(4);
    model.component("comp1").physics("ewfd").create("pmc1", "PerfectMagneticConductor", 2);
    model.component("comp1").physics("ewfd").feature("pmc1").selection().set(2, 5);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Perfect vacuum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "0[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});

    model.study("std1").feature("wave").set("plist", "lam0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u53cd\u5c04\u7387 (ewfd)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"ewfd.Rport_1", "ewfd.Rtotal"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("expr", "ewfd.Ex");
    model.result("pg1").feature("mslc1").set("descr", "\u7535\u573a\uff0cx \u5206\u91cf");
    model.result("pg1").run();

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study("std1").feature("wave").setSolveFor("/physics/gop", false);
    model.study().create("std2");
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").setSolveFor("/physics/ewfd", false);
    model.study("std2").feature("rtrac").setSolveFor("/physics/gop", true);

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensityAndPower", 0);
    model.component("comp1").physics("gop").prop("ComputePhase").setIndex("ComputePhase", 1, 0);
    model.component("comp1").physics("gop").create("rele1", "ReleaseFromElectricField", 2);
    model.component("comp1").physics("gop").feature("rele1").selection().set(4);
    model.component("comp1").physics("gop").feature("rele1").setIndex("Nr", 9, 0);
    model.component("comp1").physics("gop").feature("rele1").set("E_src", "root.comp1.ewfd.Ex");
    model.component("comp1").physics("gop").feature("rele1").set("UseFrequencyCoupledPhysics", true);

    model.study("std2").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std2").feature("rtrac").set("lunit", "\u00b5m");
    model.study("std2").feature("rtrac").set("llist", "range(0,0.1,5)");
    model.study("std2").feature("rtrac").set("usesol", true);
    model.study("std2").feature("rtrac").set("notsolmethod", "sol");
    model.study("std2").feature("rtrac").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "ray1");
    model.result("pg2").setIndex("looplevel", 51, 0);
    model.result("pg2").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg2").create("rtrj1", "RayTrajectories");
    model.result("pg2").feature("rtrj1").set("linetype", "line");
    model.result("pg2").feature("rtrj1").create("col1", "Color");
    model.result("pg2").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg2").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "bottom");
    model.result("pg2").run();
    model.result("pg2").feature("rtrj1").set("linetype", "tube");
    model.result("pg2").feature("rtrj1").set("pointtype", "arrow");
    model.result("pg2").feature("rtrj1").set("arrowtype", "arrowhead");
    model.result("pg2").feature("rtrj1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("rtrj1").feature("def1").set("expr", new String[]{"gop.Ex", "gop.Ey", "gop.Ez"});
    model.result("pg2").feature("rtrj1").feature("def1").set("descr", "\u7535\u573a");
    model.result("pg2").feature("rtrj1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("rtrj1").feature("def1").set("scale", "5E-5");
    model.result("pg2").run();
    model.result("pg2").feature("rtrj1").feature("col1").set("expr", "gop.Ex");
    model.result("pg2").feature("rtrj1").feature("col1").set("descr", "\u7535\u573a\uff0cx \u5206\u91cf");
    model.result("pg2").feature("rtrj1").feature("col1").set("colortable", "WaveLight");
    model.result("pg2").run();
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("data", "dset1");
    model.result("pg2").feature("slc1").set("expr", "ewfd.Ex");
    model.result("pg2").feature("slc1").set("descr", "\u7535\u573a\uff0cx \u5206\u91cf");
    model.result("pg2").feature("slc1").set("quickplane", "zx");
    model.result("pg2").feature("slc1").set("quickynumber", 3);
    model.result("pg2").feature("slc1").set("inheritplot", "rtrj1");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "z \u5750\u6807 (\\mu m)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "E<sub>x</sub> (V/m)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(6);
    model.result("pg3").feature("lngr1").set("expr", "ewfd.Ex");
    model.result("pg3").feature("lngr1").set("descr", "\u7535\u573a\uff0cx \u5206\u91cf");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "z");
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("data", "ray1");
    model.result("pg3").feature("glob1").setIndex("expr", "gop.ave(gop.Ex)", 0);
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "gop.gopaveop1(qz)");
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "ray1");
    model.result().numerical("gev2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev2").setIndex("expr", "gop.sum(gop.Q)", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();

    model.title("\u57fa\u4e8e\u5e73\u9762\u7535\u78c1\u6ce2\u7684\u5c04\u7ebf\u91ca\u653e");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u57fa\u4e8e\u8fb9\u754c\u5904\u7684\u5165\u5c04\u7535\u573a\u6765\u8bbe\u7f6e\u5c04\u7ebf\u91ca\u653e\u3002\u9996\u5148\u4f7f\u7528\u201c\u7535\u78c1\u6ce2\uff0c\u9891\u57df\u201d\u63a5\u53e3\u6c42\u89e3\u5e73\u9762\u6ce2\u7684\u7535\u573a\u3002\u7136\u540e\u91ca\u653e\u5c04\u7ebf\uff0c\u5176\u521d\u59cb\u5f3a\u5ea6\u548c\u504f\u632f\u4e0e\u91ca\u653e\u8fb9\u754c\u5904\u7535\u573a\u7684\u5f3a\u5ea6\u548c\u504f\u632f\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("plane_em_wave_to_ray_release.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
