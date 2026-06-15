/*
 * light_pipe.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:24 by COMSOL 6.3.0.290. */
public class light_pipe {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.component("comp1").geom("geom1").insertFile("light_pipe_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("DispersionModelSellmeierStandard", "DispersionModelSellmeierStandard", "Sellmeier");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat1")
         .label("(C5H8O2)n (Poly(methyl methacrylate), PMMA) (Sultanova et al. 2009: n 0.437-1.052 um)");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("ODsma", new String[]{"1.1819", "0", "0", "0.011313", "0", "0"});
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .set("Trefsma", "22[degC]");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard").set("Prefsma", "0");
    model.component("comp1").material("mat1").propertyGroup("DispersionModelSellmeierStandard")
         .addInput("frequency");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").addInput("frequency");

    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensityAndPower", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 1000, 0);
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("RayDirectionVector", "Conical");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Nw", 2000, 0);
    model.component("comp1").physics("gop").feature("relg1").set("cax", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("relg1").set("alphac", "pi/12");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").selection().set(22);
    model.component("comp1").physics("gop").feature("wall1").create("bsrc1", "DepositedRayPowerBoundary", 2);

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(22);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", false);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", 0.1);
    model.component("comp1").mesh("mesh1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hcurve", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "rpipe", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "rpipe", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "rb2", 0);
    model.study("std1").feature("param").setIndex("punit", "mm", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(5,2.5,20)", 0);
    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "mm");
    model.study("std1").feature("rtrac").set("llist", "range(0,15,150)");
    model.study("std1").feature("rtrac").set("charvel", "c_const/1.5");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").setIndex("looplevel", 7, 1);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.Q/1[W]*2000");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u900f\u5c04\u7387");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u66f2\u7387\u534a\u5f84\uff0c\u5f2f\u5934 2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "gop.wall1.bsrc1.Qp_int/(1[W])", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u900f\u5c04\u7387", 0);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").run();

    model.title("\u5149\u5bfc\u7ba1");

    model
         .description("\u5149\u5bfc\u7ba1\u662f\u4e00\u79cd\u53ef\u4ee5\u7528\u6765\u5728\u4e0d\u540c\u4f4d\u7f6e\u4f20\u5bfc\u5149\u7ebf\u7684\u7ed3\u6784\u3002\u901a\u5e38\u60c5\u51b5\u4e0b\uff0c\u53ef\u4ee5\u5206\u4e3a\u4e24\u79cd\uff1a\u7ba1\u58c1\u5e26\u6709\u53cd\u5c04\u6d82\u5c42\uff0c\u4ee5\u53ca\u901a\u8fc7\u5168\u5185\u53cd\u5c04\u4f20\u8f93\u5149\u7ebf\u7684\u900f\u660e\u56fa\u4f53\u3002\u5728\u672c\u4f8b\u4e2d\uff0c\u5149\u7ebf\u901a\u8fc7\u5168\u5185\u53cd\u5c04\u5728\u5f2f\u66f2\u5149\u5bfc\u7ba1\u4e2d\u4f20\u64ad\uff0c\u7814\u7a76\u4e86\u7ba1\u7684\u5f62\u72b6\u5bf9\u900f\u5149\u7387\u7684\u5f71\u54cd\u3002");

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

    model.label("light_pipe.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
