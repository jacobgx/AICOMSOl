/*
 * vacuum_drying.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class vacuum_drying {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Chemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");
    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("c").field("dimensionless").field("theta");
    model.component("comp1").physics("c").field("dimensionless").component(new String[]{"thetaL"});
    model.component("comp1").physics("c").prop("Units").set("CustomSourceTermUnit", "1/s");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/physics/c", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("A", "5.31384", "\u5b89\u6258\u4e07\u65b9\u7a0b\u5e38\u6570");
    model.param().set("alpha", "1e-6[m^2/s]", "\u8868\u89c2\u6269\u6563\u7cfb\u6570\u7684\u6bd4\u4f8b\u5e38\u6570");
    model.param().set("B", "1690.864[K]", "\u5b89\u6258\u4e07\u65b9\u7a0b\u5e38\u6570");
    model.param().set("C", "-51.804[K]", "\u5b89\u6258\u4e07\u65b9\u7a0b\u5e38\u6570");
    model.param().set("CpG", "1[kJ/(kg*K)]", "\u6bd4\u70ed\u5bb9\uff0c\u6c14\u4f53");
    model.param().set("CpL", "2.544[kJ/(kg*K)]", "\u6bd4\u70ed\u5bb9\uff0c\u6db2\u4f53");
    model.param().set("CpS", "2060[J/(kg*K)]", "\u6bd4\u70ed\u5bb9\uff0c\u56fa\u4f53");
    model.param().set("deltaH", "9780[cal/mol]/Mn", "\u6c7d\u5316\u6f5c\u70ed");
    model.param().set("H0", "10[cm]", "\u6ee4\u997c\u6df1\u5ea6");
    model.param().set("hq", "10[W/(m^2*K)]", "\u58c1\u4f20\u70ed\u7cfb\u6570");
    model.param().set("kvap", "1e-6[1/s]", "\u84b8\u53d1\u901f\u7387\u5e38\u6570");
    model.param().set("lambda_dry", ".058[W/(m*K)]", "\u6709\u6548\u5bfc\u70ed\u7cfb\u6570\uff0c\u5e72\u6ee4\u997c");
    model.param().set("lambda_wet", ".1[W/(m*K)]", "\u6709\u6548\u5bfc\u70ed\u7cfb\u6570\uff0c\u6e7f\u6ee4\u997c");
    model.param().set("Mn", "60.1[g/mol]", "\u6469\u5c14\u8d28\u91cf");
    model.param().set("pG", "15[mbar]", "\u9876\u90e8\u7a7a\u95f4\u538b\u529b");
    model.param().set("R0", "40[cm]", "\u6ee4\u997c\u534a\u5f84");
    model.param().set("rhoG", "1[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6c14\u4f53");
    model.param().set("rhoL", "787.4[kg/m^3]", "\u5bc6\u5ea6\uff0c\u6db2\u4f53");
    model.param().set("rhoS", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u56fa\u4f53");
    model.param().set("T0", "20[degC]", "\u521d\u59cb\u6e29\u5ea6");
    model.param().set("Th", "60[degC]", "\u5939\u5957\u6e29\u5ea6");
    model.param().set("thetaL_star", ".05", "\u6b8b\u4f59\u9971\u548c\u5ea6");
    model.param().set("thetaS", "0.7", "\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("wL0", ".20", "\u521d\u59cb\u6e7f\u5ea6");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("mdot", "kvap*rhoL*(p_star-pG)/pG*step2(thetaL)*step1(p_star/pG)", "\u84b8\u53d1\u7387");
    model.component("comp1").variable("var1")
         .set("p_star", "10^(A-B/(C+T))*1e5[Pa]", "\u4ece\u5b89\u6258\u4e07\u65b9\u7a0b\u5f97\u5230\u7684\u84b8\u6c7d\u538b");
    model.component("comp1").variable("var1")
         .set("thetaG", "1-thetaL-thetaS", "\u6c14\u4f53\u7684\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("DL", "alpha*(thetaL-thetaL_star)*step1(thetaL/thetaL_star)", "\u8868\u89c2\u6c34\u5206\u6269\u6563\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("thetaL0", "thetaS*(rhoS/rhoL)*(wL0/(1-wL0))", "\u6db2\u4f53\u7684\u521d\u59cb\u4f53\u79ef\u5206\u6570");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 1.005);
    model.component("comp1").func("step1").set("smooth", 0.01);
    model.component("comp1").func().create("step2", "Step");
    model.component("comp1").func("step2").set("location", 0.005);
    model.component("comp1").func("step2").set("smooth", 0.01);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R0", "H0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mpmat1", "Multiphase");
    model.component("comp1").material("mpmat1").feature("phase1").label("\u53d7\u7ea6\u675f\u7684\u6c14\u76f8");
    model.component("comp1").material("mpmat1").feature("phase1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_dry/(1-thetaS)"});
    model.component("comp1").material("mpmat1").feature("phase1").propertyGroup("def")
         .set("density", new String[]{"rhoG"});
    model.component("comp1").material("mpmat1").feature("phase1").propertyGroup("def")
         .set("heatcapacity", new String[]{"CpG"});
    model.component("comp1").material("mpmat1").feature().create("phase2", "PhaseLink", "comp1");
    model.component("comp1").material("mpmat1").feature("phase2").label("\u6db2\u76f8");
    model.component("comp1").material("mpmat1").feature("phase2").set("Vf", "thetaL");
    model.component("comp1").material("mpmat1").feature("phase2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"lambda_wet/(1-thetaS)"});
    model.component("comp1").material("mpmat1").feature("phase2").propertyGroup("def")
         .set("density", new String[]{"rhoL"});
    model.component("comp1").material("mpmat1").feature("phase2").propertyGroup("def")
         .set("heatcapacity", new String[]{"CpL"});
    model.component("comp1").material("mpmat1").feature().create("phase3", "PhaseLink", "comp1");
    model.component("comp1").material("mpmat1").feature("phase3").label("\u56fa\u76f8");
    model.component("comp1").material("mpmat1").feature("phase3").set("Vf", "thetaS");
    model.component("comp1").material("mpmat1").feature("phase3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0"});
    model.component("comp1").material("mpmat1").feature("phase3").propertyGroup("def")
         .set("density", new String[]{"rhoS"});
    model.component("comp1").material("mpmat1").feature("phase3").propertyGroup("def")
         .set("heatcapacity", new String[]{"CpS"});

    model.component("comp1").physics("ht").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("ht").feature("hs1").selection().set(1);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "-mdot*deltaH");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(2, 4);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "hq");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "Th");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", new String[]{"DL", "0", "0", "DL"}, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("f", "-mdot/rhoL", 0);
    model.component("comp1").physics("c").feature("init1").set("thetaL", "thetaL0");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_thetaL").set("scalemethod", "manual");

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,1,70)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 71, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg2").feature("surf1").set("expr", "thetaL");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "rev1");
    model.result("pg3").setIndex("looplevel", 71, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").label("\u7cfb\u6570\u5f62\u5f0f\u504f\u5fae\u5206\u65b9\u7a0b 1");
    model.result("pg3").feature("surf1").set("expr", "thetaL");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 31, 0);
    model.result("pg3").run();
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset1");
    model.result().dataset("rev2")
         .set("defaultPlotIDs", new String[]{"ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg2|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg3|ht", "ht/HT_PhysicsInterfaces/icom8/pdef1/pcond2/pcond4/pcond2/pg4|ht"});
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg4").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u8868\u89c2\u6c34\u5206\u6269\u6563\u7cfb\u6570");
    model.result("pg5").setIndex("looplevel", 31, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "DL");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u84b8\u53d1\u7387");
    model.result("pg6").setIndex("looplevel", 31, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "mdot");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 21, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.title("\u771f\u7a7a\u5e72\u71e5");

    model
         .description("\u771f\u7a7a\u5e72\u71e5\u662f\u5236\u836f\u548c\u98df\u54c1\u5de5\u4e1a\u4e2d\u5e38\u7528\u4e8e\u53bb\u9664\u6f6e\u6e7f\u7c89\u672b\u4e2d\u7684\u6c34\u5206\u6216\u6709\u673a\u6eb6\u5242\u7684\u5316\u5de5\u5de5\u827a\u3002\u5728\u8bbe\u8ba1\u771f\u7a7a\u5e72\u71e5\u7cfb\u7edf\u65f6\uff0c\u5de5\u7a0b\u5e08\u4f1a\u5728\u786e\u4fdd\u4ea7\u54c1\u9ad8\u8d28\u91cf\u7684\u540c\u65f6\uff0c\u5c3d\u53ef\u80fd\u5730\u7f29\u77ed\u5e72\u71e5\u65f6\u95f4\u3002\u8be5\u6a21\u578b\u7814\u7a76\u7531\u5185\u542b\u6e7f\u6ee4\u997c\u7684\u5706\u67f1\u5f62\u6eda\u7b52\u6784\u6210\u7684 Nutsche \u8fc7\u6ee4\u5e72\u71e5\u673a\u4e2d\u7684\u771f\u7a7a\u5e72\u71e5\u3002\u6e7f\u6ee4\u997c\u7684\u4e0a\u65b9\u66b4\u9732\u5728\u4f4e\u538b\u9876\u90e8\u7a7a\u95f4\u4e2d\uff0c\u4fa7\u58c1\u548c\u5e95\u90e8\u58c1\u5219\u66b4\u9732\u4e8e\u52a0\u70ed\u6d41\u4f53\u4e2d\u3002\u5e72\u71e5\u673a\u901a\u8fc7\u5728\u975e\u5e38\u4f4e\u7684\u538b\u529b\u548c\u9ad8\u6e29\u4e0b\u5de5\u4f5c\uff0c\u4f7f\u6db2\u4f53\u7684\u84b8\u53d1\u7387\u589e\u52a0\uff0c\u4ece\u800c\u52a0\u901f\u7c89\u672b\u7684\u5e72\u71e5\u8fc7\u7a0b\u3002\u672c\u4f8b\u6a21\u62df\u7c89\u672b\u4e2d\u6eb6\u5242\u7684\u4f20\u70ed\u548c\u84b8\u53d1\uff0c\u4ee5\u7814\u7a76\u6e29\u5ea6\u548c\u6db2\u76f8\u5206\u5e03\uff1b\u5176\u4e2d\u57fa\u4e8e Murru \u7b49\u4eba\u53d1\u8868\u7684\u5173\u4e8e\u5728\u9759\u6001\u6761\u4ef6\u4e0b\u7528\u6b63\u4e19\u9187\u5e72\u71e5\u7c89\u672b\u7684\u8bba\u6587\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("vacuum_drying.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
