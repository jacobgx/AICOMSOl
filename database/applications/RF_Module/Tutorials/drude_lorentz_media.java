/*
 * drude_lorentz_media.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:43 by COMSOL 6.3.0.290. */
public class drude_lorentz_media {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("temw", "TransientElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/temw", true);

    model.param().set("lda0", "1[um]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("E0", "1[V/m]");
    model.param().descr("E0", "\u7535\u573a\u5927\u5c0f");
    model.param().set("k0", "2*pi/lda0");
    model.param().descr("k0", "\u771f\u7a7a\u4e2d\u7684\u6ce2\u6570");
    model.param().set("t0", "25[fs]");
    model.param().descr("t0", "\u5ef6\u8fdf\u65f6\u95f4");
    model.param().set("dt", "10[fs]");
    model.param().descr("dt", "\u8109\u51b2\u6301\u7eed\u65f6\u95f4");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("omega0", "2*pi[rad]*c_const/lda0");
    model.component("comp1").variable("var1").descr("omega0", "\u89d2\u9891\u7387");
    model.component("comp1").variable("var1").set("E_bnd", "E0*cos(omega0*t-k0*x)");
    model.component("comp1").variable("var1").descr("E_bnd", "\u7535\u573a\u7684\u5e73\u9762\u6ce2\u56e0\u5b50");
    model.component("comp1").variable("var1").set("E_pulse", "exp(-(t-t0)^2/dt^2)");
    model.component("comp1").variable("var1").descr("E_pulse", "\u7535\u573a\u7684\u65f6\u95f4\u56e0\u5b50");
    model.component("comp1").variable("var1").set("omega_p", "1.5*omega0");
    model.component("comp1").variable("var1").descr("omega_p", "\u7b49\u79bb\u5b50\u4f53\u9891\u7387");
    model.component("comp1").variable("var1").set("omega_1", "0.5*omega_p");
    model.component("comp1").variable("var1").descr("omega_1", "\u8c10\u632f\u9891\u7387");
    model.component("comp1").variable("var1").set("gamma_1", "0.1*omega_1");
    model.component("comp1").variable("var1").descr("gamma_1", "\u963b\u5c3c\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"lda0", "6*lda0"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"20*lda0", "6*lda0"});
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r1");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"lda0", "0.5*lda0"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().set(5);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop3").selection().set(9);

    model.component("comp1").physics("temw").prop("components").set("components", "inplane");
    model.component("comp1").physics("temw").feature("wee1")
         .set("DisplacementFieldModel", "DrudeLorentzDispersionModel");
    model.component("comp1").physics("temw").feature("wee1").set("epsilonInf", new int[]{4, 0, 0, 0, 4, 0, 0, 0, 1});
    model.component("comp1").physics("temw").feature("wee1").set("omegap", "omega_p");
    model.component("comp1").physics("temw").feature("wee1").set("mur_mat", "userdef");
    model.component("comp1").physics("temw").feature("wee1").set("sigma_mat", "userdef");
    model.component("comp1").physics("temw").feature("wee1").create("dlp1", "DrudeLorentzPolarization", 2);
    model.component("comp1").physics("temw").feature("wee1").feature("dlp1").set("item.f", 1);
    model.component("comp1").physics("temw").feature("wee1").feature("dlp1").set("item.omega0", "omega_1");
    model.component("comp1").physics("temw").feature("wee1").feature("dlp1").set("item.damp", "gamma_1");
    model.component("comp1").physics("temw").create("wee2", "WaveEquationElectric", 2);
    model.component("comp1").physics("temw").feature("wee2").selection().set(1, 3, 5);
    model.component("comp1").physics("temw").feature("wee2").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("temw").feature("wee2").set("mur_mat", "userdef");
    model.component("comp1").physics("temw").feature("wee2").set("sigma_mat", "userdef");
    model.component("comp1").physics("temw").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("temw").feature("sctr1").set("IncidentField", "EField");
    model.component("comp1").physics("temw").feature("sctr1").selection().set(1);
    model.component("comp1").physics("temw").feature("sctr1").set("E0i", new String[]{"0", "E_pulse*E_bnd", "0"});
    model.component("comp1").physics("temw").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("temw").feature("sctr2").selection().set(16);
    model.component("comp1").physics("temw").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("temw").feature("pc1").selection().set(2, 3, 5, 10, 12, 15);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(3, 10, 15);
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").set(3, 10, 15);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").set(2, 5, 12);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lda0/6");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,10[fs],100[fs])");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "0.1[fs]");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "intop1(temw.Ey)");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").set("expr", "intop2(temw.Poscy)");
    model.component("comp1").probe("var2").set("window", "new");
    model.component("comp1").probe().create("var3", "GlobalVariable");
    model.component("comp1").probe("var3").set("expr", "intop3(temw.Poscy)");
    model.component("comp1").probe("var3").set("window", "new");

    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("var3").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("solvertype", "none");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solvertype", "none");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solvertype", "none");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solvertype", "none");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solvertype", "none");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solvertype", "none");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "temw.Poscy");
    model.result("pg4").run();
    model.result("pg1").set("window", "window1");
    model.result("pg1").run();
    model.result("pg2").set("window", "window2");
    model.result("pg2").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg2").run();
    model.result("pg3").set("window", "window3");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "temw.Hz");
    model.result("pg5").feature("surf1").set("colortable", "Cyclic");
    model.result("pg5").run();
    model.result("pg5").create("con1", "Contour");
    model.result("pg5").feature("con1").set("expr", "temw.Poscy");
    model.result("pg5").run();

    model.title("Drude-Lorentz \u8272\u6563\u4ecb\u8d28\u7684\u65f6\u57df\u5efa\u6a21");

    model
         .description("\u8868\u9762\u7b49\u79bb\u6fc0\u5143\u7a7a\u7a74\u9635\u5217\u5bf9\u4e8e\u5c0f\u4e8e\u6ce2\u957f\u7684\u7a7a\u7a74\u4e5f\u53ef\u4ee5\u8868\u73b0\u51fa\u8f83\u5927\u7684\u900f\u5c04\u7387\uff0c\u8fd9\u662f\u7531\u4e8e\u5b58\u5728\u8868\u9762\u7b49\u79bb\u6781\u5316\u6fc0\u5143\uff0c\u5373\u4f7f\u5b83\u8fdc\u5c0f\u4e8e\u6ce2\u957f\uff0c\u4e5f\u80fd\u901a\u8fc7\u7a7a\u7a74\u96a7\u7a7f\u7535\u78c1\u80fd\u3002\n\n\u672c\u4f8b\u65e8\u5728\u4f5c\u4e3a\u4e00\u4e2a\u6559\u7a0b\uff0c\u6f14\u793a\u5982\u4f55\u5bf9\u8272\u6563\u4ecb\u8d28\u4e2d\u7684\u5168\u77ac\u6001\u6ce2\u52a8\u65b9\u7a0b\u8fdb\u884c\u5efa\u6a21\uff0c\u5176\u4e2d\u7684\u6781\u5316\u53ef\u4ee5\u8868\u793a\u4e3a Drude-Lorentz \u5171\u632f\u9879\u7684\u548c\u3002\u6bcf\u4e2a Drude-Lorentz \u6781\u5316\u573a\u5747\u901a\u8fc7\u7535\u573a\u9a71\u52a8\u7684\u5e38\u5fae\u5206\u65b9\u7a0b\u6c42\u89e3\u3002\n\n\u8be5\u4e8c\u7ef4\u51e0\u4f55\u7531\u4e00\u4e2a\u539a\u5ea6\u4e3a 1 \u00b5m \u7684\u8272\u6563\u677f\u7ec4\u6210\uff0c\u5176\u4e2d\u6709\u4e00\u6761 0.5 \u00b5m \u5bbd\u7684\u72ed\u7f1d\u3002\u91c7\u7528\u7684\u6ce2\u957f\u4e3a 1 \u00b5m\u3002\u672c\u4f8b\u5e94\u7528\u5468\u671f\u6027\u8fb9\u754c\u6761\u4ef6\uff0c\u56e0\u6b64\u8fd9\u5b9e\u9645\u4e0a\u662f\u4e00\u4e2a\u72ed\u7f1d\u9635\u5217\u3002\u5165\u5c04\u6ce2\u662f\u5177\u6709\u5e73\u9762\u6ce2\u524d\u4e14\u65f6\u95f4\u6ce2\u5f62\u4e3a\u9ad8\u65af\u5206\u5e03\u7684\u5e73\u9762\u6ce2\u8109\u51b2\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("drude_lorentz_media.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
