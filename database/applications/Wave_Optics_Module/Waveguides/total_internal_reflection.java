/*
 * total_internal_reflection.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:45 by COMSOL 6.3.0.290. */
public class total_internal_reflection {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Waveguides");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

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
    model.study("std1").feature("wave").setSolveFor("/physics/ewbe", true);

    model.param().set("lda0", "1[um]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("k0", "2*pi/lda0");
    model.param().descr("k0", "\u771f\u7a7a\u6ce2\u6570");
    model.param().set("n", "1.5");
    model.param().descr("n", "\u6ce2\u5bfc\u4e2d\u7684\u6298\u5c04\u7387");
    model.param().set("k", "k0*n");
    model.param().descr("k", "\u6ce2\u5bfc\u4e2d\u7684\u6ce2\u6570");
    model.param().set("theta", "10[deg]");
    model.param().descr("theta", "\u5165\u5c04\u89d2");
    model.param().set("k1x", "k*cos(theta)");
    model.param().descr("k1x", "\u6ce2\u77e2\uff0c\u7b2c\u4e00\u4e2a\u6ce2\uff0cx \u5206\u91cf");
    model.param().set("k1y", "k*sin(theta)");
    model.param().descr("k1y", "\u6ce2\u77e2\uff0c\u7b2c\u4e00\u4e2a\u6ce2\uff0cy \u5206\u91cf");
    model.param().set("d", "350[um]");
    model.param().descr("d", "\u6ce2\u5bfc\u5bbd\u5ea6");
    model.param().set("L", "5*4*d/2/tan(theta)");
    model.param().descr("L", "\u6ce2\u5bfc\u957f\u5ea6");
    model.param().set("w0", "75[um]");
    model.param().descr("w0", "\u5149\u675f\u534a\u5f84");

    model.component("comp1").view("view1").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view1").axis().set("yscale", 10);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "d"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-d/2"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n"});

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("k1", new String[]{"k1x", "k1y", "0"});
    model.component("comp1").physics("ewbe").prop("WaveVector")
         .set("k2", new String[]{"ewbe.k1x", "-ewbe.k1y", "0"});
    model.component("comp1").physics("ewbe").create("mbc1", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("mbc1").selection().set(1);
    model.component("comp1").physics("ewbe").feature("mbc1").set("IncidentField", "GaussianBeam");
    model.component("comp1").physics("ewbe").feature("mbc1").set("w0", "w0");
    model.component("comp1").physics("ewbe").feature("mbc1").set("Eg0", new String[]{"0", "0", "1[V/m]"});
    model.component("comp1").physics("ewbe").feature("mbc1").set("NoScatteredField", true);
    model.component("comp1").physics("ewbe").create("mbc2", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewbe").feature("mbc2").selection().set(4);
    model.component("comp1").physics("ewbe").feature("mbc2").set("InputWave", "SecondWave");
    model.component("comp1").physics("ewbe").create("imp1", "Impedance", 1);
    model.component("comp1").physics("ewbe").feature("imp1").selection().set(2, 3);
    model.component("comp1").physics("ewbe").feature("imp1").set("n_mat", "userdef");
    model.component("comp1").physics("ewbe").feature("imp1").set("ki_mat", "userdef");
    model.component("comp1").physics("ewbe").prop("MeshControl").set("elemCountT", 100);
    model.component("comp1").physics("ewbe").prop("MeshControl").set("elemCountL", 400);

    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a\uff0c\u7b2c\u4e00\u4e2a\u6ce2 (ewbe)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u7535\u573a");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "ewbe.normE1");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a\uff0c\u7b2c\u4e8c\u4e2a\u6ce2 (ewbe)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u7535\u573a");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "ewbe.normE2");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u573a (ewbe)");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "ewbe.normE");
    model.result("pg3").feature("surf1").set("resolution", "extrafine");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u573a\uff0c\u900f\u89c6\u56fe");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("recover", "ppr");
    model.result("pg4").feature("surf1").create("hght1", "Height");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("hght1").set("isheightaxisshown", false);
    model.result("pg4").run();

    model.title("\u5168\u5185\u53cd\u5c04");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u63a5\u53e3\u7684\u53cc\u5411\u516c\u5f0f\u6a21\u62df\u6cbf\u6ce2\u5bfc\u4f20\u64ad\u7684\u5149\u675f\u5728\u4e24\u4e2a\u4e3b\u8981\u65b9\u5411\u7684\u53cd\u5c04\u60c5\u51b5\u3002\n\n\u4e00\u675f\u51e0\u4e4e\u51c6\u76f4\u7684\u9ad8\u65af\u5149\u675f\u5728\u5de6\u8fb9\u754c\u88ab\u6fc0\u53d1\u540e\uff0c\u5728\u6ce2\u5bfc\u4e0e\u5468\u56f4\u7a7a\u6c14\u4e4b\u95f4\u7684\u754c\u9762\u4e0a\u8868\u73b0\u51fa\u5168\u5185\u53cd\u5c04 (TIR)\u3002\u5149\u675f\u6cbf\u6ce2\u5bfc\u4f20\u64ad 20\u00a0mm \u540e\u79bb\u5f00\u6ce2\u5bfc\u3002\n\n\u6b64\u7c7b\u5149\u6ce2\u5bfc\u5bf9\u865a\u62df\u73b0\u5b9e (VR) \u4eff\u771f\u975e\u5e38\u6709\u7528\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("total_internal_reflection.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
