/*
 * annular_ultraviolet_reactor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:25 by COMSOL 6.3.0.290. */
public class annular_ultraviolet_reactor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Ultraviolet_Sterilization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().set("r_lamp", "1[cm]");
    model.param().descr("r_lamp", "\u706f\u534a\u5f84");
    model.param().set("r_reac", "5[cm]");
    model.param().descr("r_reac", "\u53cd\u5e94\u5668\u534a\u5f84");
    model.param().set("L_reac", "100[cm]");
    model.param().descr("L_reac", "\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param().set("L_lamp", "80[cm]");
    model.param().descr("L_lamp", "\u706f\u957f\u5ea6");
    model.param().set("d_lamp", "L_reac-L_lamp");
    model.param().descr("d_lamp", "\u706f\u4f4d\u79fb");
    model.param().set("mid_lamp", "d_lamp+L_lamp/2");
    model.param().descr("mid_lamp", "\u706f\u4e2d\u9762\u4f4d\u7f6e");
    model.param().set("P", "40[W]");
    model.param().descr("P", "\u603b\u6e90\u529f\u7387");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u53cd\u5e94\u5668");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r_reac");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L_reac");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").label("\u706f");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "r_lamp");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "L_lamp");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "d_lamp"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("genproj1", "GeneralProjection");
    model.component("comp1").cpl("genproj1").selection().set(1);
    model.component("comp1").cpl("genproj1").set("srcmap", new String[]{"sqrt(x^2+y^2)", "y", "z"});
    model.component("comp1").cpl("genproj1").setIndex("srcmap", "z", 1);
    model.component("comp1").cpl("genproj1").setIndex("srcmap", "atan2(y,x)", 2);
    model.component("comp1").cpl("genproj1").set("dstmap", new String[]{"sqrt(x^2+y^2)", "y"});
    model.component("comp1").cpl("genproj1").setIndex("dstmap", "z", 1);

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("OnlyStoreAccumulatedVariables")
         .setIndex("OnlyStoreAccumulatedVariables", 1, 0);
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputePower", 0);
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "254[nm]");
    model.component("comp1").physics("gop").feature("mp1").set("n_mat", "userdef");
    model.component("comp1").physics("gop").feature("mp1")
         .set("n", new double[]{1.38, 0, 0, 0, 1.38, 0, 0, 0, 1.38});
    model.component("comp1").physics("gop").feature("mp1").set("OpticalAttenuationModel", "InternalTransmittance10");
    model.component("comp1").physics("gop").feature("mp1").set("taui10_mat", "userdef");
    model.component("comp1").physics("gop").feature("mp1").set("taui10", 0.7);
    model.component("comp1").physics("gop").create("relb1", "ReleaseFromBoundary", 2);
    model.component("comp1").physics("gop").feature("relb1").selection().set(5, 6, 9, 10);
    model.component("comp1").physics("gop").feature("relb1").set("InitialPosition", "Density");
    model.component("comp1").physics("gop").feature("relb1").setIndex("Nr", 100000, 0);
    model.component("comp1").physics("gop").feature("relb1").set("RayDirectionVector", "Lambertian");
    model.component("comp1").physics("gop").feature("relb1").set("SpecifyInletTangentialNormal", true);
    model.component("comp1").physics("gop").feature("relb1").setIndex("Nw", 1, 0);
    model.component("comp1").physics("gop").feature("relb1").set("rax", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("relb1").set("SamplingFromDistribution", "Random");
    model.component("comp1").physics("gop").feature("relb1").set("Psrc", "P");
    model.component("comp1").physics("gop").create("frc1", "FluenceRateCalculation", 3);
    model.component("comp1").physics("gop").feature("frc1").selection().set(1);
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").selection().all();
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").selection().set(1, 2, 3, 4, 8, 11);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u5438\u6536\u58c1");
    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "0 0.2");
    model.study("std1").feature("rtrac").set("useadvanceddisable", true);
    model.study("std1").feature("rtrac").set("disabledphysics", new String[]{"gop/mir1"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u6ce8\u91cf\u7387\u5207\u9762\u56fe");
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("expr", "gop.frc1.E0");
    model.result("pg1").feature("slc1").set("descr", "\u6ce8\u91cf\u7387");
    model.result("pg1").feature("slc1").set("unit", "mW/cm^2");
    model.result("pg1").feature("slc1").set("quickxnumber", 1);
    model.result("pg1").feature("slc1").set("resolution", "norefine");
    model.result("pg1").feature("slc1").set("colortable", "Magma");
    model.result("pg1").feature("slc1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("slc1").set("colorcalibration", -1.5);
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "r_lamp", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "mid_lamp", 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", "r_reac", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "mid_lamp", 1, 2);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u6ce8\u91cf\u7387\u5f84\u5411\u5206\u5e03");
    model.result("pg2").set("data", "cln1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").set("expr", "genproj1(gop.frc1.E0)/genproj1(1)");
    model.result("pg2").feature("lngr1").set("unit", "mW/cm^2");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").set("xdataunit", "cm");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "\u65b9\u4f4d\u89d2\u5e73\u5747\u6ce8\u91cf\u7387", 0);
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("expr", "gop.frc1.E0");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u6cbf\u622a\u7ebf\u7684\u6ce8\u91cf\u7387", 0);
    model.result("pg2").feature().duplicate("lngr3", "lngr2");
    model.result("pg2").run();
    model.result("pg2").feature("lngr3").set("expr", "P/L_lamp/(2*pi*x)*0.7^((x-r_lamp)/1[cm])");
    model.result("pg2").feature("lngr3").setIndex("legends", "\u7b80\u5316\u7684\u5f84\u5411\u6a21\u578b", 0);
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").setSolveFor("/physics/gop", true);
    model.study("std2").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std2").feature("rtrac").set("llist", "0 0.2");
    model.study("std2").label("\u7814\u7a76 2\uff1a\u53cd\u5c04\u58c1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").set("data", "dset2");
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u5438\u6536\u53cd\u5e94\u5668 vs. \u53cd\u5c04\u53cd\u5e94\u5668");
    model.result("pg3").run();
    model.result("pg3").feature().remove("lngr2");
    model.result("pg3").feature().remove("lngr3");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").setIndex("legends", "\u6ce8\u91cf\u7387\uff0c\u5438\u6536\u58c1", 0);
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("data", "cln2");
    model.result("pg3").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg3").feature("lngr2").setIndex("legends", "\u6ce8\u91cf\u7387\uff0c\u53cd\u5c04\u58c1", 0);
    model.result("pg3").run();

    model.title("\u73af\u5f62\u7d2b\u5916\u53cd\u5e94\u5668");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u7d2b\u5916\u7ebf (UV) \u53cd\u5e94\u5668\u7684\u4f53\u79ef\u6ce8\u91cf\u7387\u3002\u51e0\u4f55\u5f62\u72b6\u662f\u56f4\u7ed5\u5706\u67f1\u5f62\u706f\u7684\u73af\u5f62\u6d41\u4f53\u533a\u57df\u3002\u672c\u4f8b\u8003\u8651\u4e86\u53cd\u5e94\u5668\u58c1\u4e0a\u7684\u53cd\u5c04\u5bf9\u5f84\u5411\u6ce8\u91cf\u7387\u5206\u5e03\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("annular_ultraviolet_reactor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
