/*
 * jet_instability_mm.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:12 by COMSOL 6.3.0.290. */
public class jet_instability_mm {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Two-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").prop("ConsistentStabilization").set("CrosswindDiffusion", "0");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "yeoh");
    model.component("comp1").common("free1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{5, 60});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -30});
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("rhoink", "1e3[kg/m^3]");
    model.param().descr("rhoink", "\u5bc6\u5ea6\uff0c\u58a8\u6c34");
    model.param().set("etaink", "1e-3[Pa*s]");
    model.param().descr("etaink", "\u52a8\u529b\u9ecf\u5ea6\uff0c\u58a8\u6c34");
    model.param().set("sigma0", "0.07[N/m]");
    model.param().descr("sigma0", "\u53c2\u8003\u8868\u9762\u5f20\u529b\u7cfb\u6570");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("sigma", "sigma0*(1-0.2*cos(2*pi*Z/60[um]))");
    model.component("comp1").variable("var1").descr("sigma", "\u8868\u9762\u5f20\u529b\u7cfb\u6570");

    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("rho", "rhoink");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("mu", "etaink");
    model.component("comp1").physics("spf").create("fs1", "FreeSurface", 1);
    model.component("comp1").physics("spf").feature("fs1").selection().set(4);
    model.component("comp1").physics("spf").feature("fs1").set("SurfaceTensionCoefficient", "userdef");
    model.component("comp1").physics("spf").feature("fs1").set("sigma", "sigma");
    model.component("comp1").physics("spf").feature("wallbc1").set("BoundaryCondition", "NavierSlip");

    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set(2, 3);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.5e-6,1e-5)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").setEntry("atolmethod", "comp1_u", "unscaled");
    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "surface");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "spf.rho");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "black");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 13, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 19, 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "sigma");
    model.result("pg6").run();
    model.result("pg3").run();

    model.title("\u5c04\u6d41\u4e0d\u7a33\u5b9a\u6027 - \u52a8\u7f51\u683c");

    model
         .description("\u5f53\u8868\u9762\u5f20\u529b\u7cfb\u6570\u5b58\u5728\u68af\u5ea6\u65f6\uff0c\u9a6c\u5170\u6208\u5c3c\u6548\u5e94\u4f1a\u5728\u6d41\u4f53/\u6d41\u4f53\u754c\u9762\u7684\u5207\u5411\u4ea7\u751f\u6ed1\u79fb\u901f\u5ea6\u3002\u5728\u672c\u4f8b\u4e2d\uff0c\u7531\u4e8e\u5b58\u5728\u5468\u671f\u6027\u7684\u8868\u9762\u5f20\u529b\u68af\u5ea6\uff0c\u8f83\u957f\u7684\u5c04\u6d41\u5728\u60ef\u6027\u53c2\u8003\u7cfb\u4e2d\u7834\u788e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("jet_instability_mm.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
