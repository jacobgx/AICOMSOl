/*
 * vdara_caustic_surface.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:20 by COMSOL 6.3.0.290. */
public class vdara_caustic_surface {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Solar_Radiation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.component("comp1").geom("geom1").lengthUnit("km");
    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "vdara_caustic_surface.x_b");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u9152\u5e97\u8868\u9762");
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("xmin", 0.475);
    model.component("comp1").selection("box1").set("xmax", 0.52);
    model.component("comp1").selection("box1").set("ymin", 0.38);
    model.component("comp1").selection("box1").set("ymax", 0.5);
    model.component("comp1").selection("box1").set("zmin", 0.01);
    model.component("comp1").selection("box1").set("zmax", 0.2);

    model.component("comp1").physics("gop").selection().set();
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensity", 0);
    model.component("comp1").physics("gop").prop("StoreRayStatusData").setIndex("StoreRayStatusData", 1, 0);
    model.component("comp1").physics("gop").create("ill1", "IlluminatedSurface", 2);
    model.component("comp1").physics("gop").feature("ill1").selection().set(351, 356, 359);
    model.component("comp1").physics("gop").feature("ill1").set("InitialPosition", "Density");
    model.component("comp1").physics("gop").feature("ill1").setIndex("Nr", 50000, 0);
    model.component("comp1").physics("gop").feature("ill1").set("IncidentRayDirectionVector", "SolarRadiation");
    model.component("comp1").physics("gop").feature("ill1").set("LocationDefinedBy", "City");
    model.component("comp1").physics("gop").feature("ill1").set("Date", new String[]{"01", "9", "2014"});
    model.component("comp1").physics("gop").feature("ill1").set("LocalTime", new int[]{11, 45, 0});
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").selection().set(321, 331, 345);
    model.component("comp1").physics("gop").feature("wall1").create("bacc1", "BoundaryAccumulator", 2);
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1")
         .set("AccumulateOver", "RaysInBoundaryElements");
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1").set("R", "gop.logI");
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1")
         .set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1")
         .set("DependentVariableQuantity", "none");
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1")
         .setIndex("CustomDependentVariableUnit", "m^-2", 0, 0);
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").selection().named("box1");
    model.component("comp1").physics("gop").feature("wall2").set("WallCondition", "SpecularReflection");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "range(0,10,200)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").set("extrasteps", "none");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset1");
    model.result().dataset("grid1").set("parmin1", 0.11538037905865195);
    model.result().dataset("grid1").set("parmax1", 0.4615950947646629);
    model.result().dataset("grid1").set("res1", 3);
    model.result().dataset("grid1").set("parmin2", 0.11487941492843083);
    model.result().dataset("grid1").set("parmax2", 0.47451839772521454);
    model.result().dataset("grid1").set("res2", 3);
    model.result().dataset("grid1").set("parmin3", 0.03657996);
    model.result().dataset("grid1").set("parmax3", 0.14631999);
    model.result().dataset("grid1").set("res3", 3);
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "grid1");
    model.result().dataset("cpt1").set("method", "regulargrid");
    model.result().dataset("cpt1").set("regulargridx", 3);
    model.result().dataset("cpt1").set("regulargridy", 3);
    model.result().dataset("cpt1").set("regulargridz", 3);
    model.result("pg1").create("arpt1", "ArrowPoint");
    model.result("pg1").feature("arpt1").set("data", "cpt1");
    model.result("pg1").feature("arpt1")
         .set("expr", new String[]{"gop.ill1.nsolx", "gop.ill1.nsoly", "gop.ill1.nsolz"});
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u53cd\u5c04\u7ebf\u548c\u73af\u5883\u592a\u9633\u8f90\u5c04");
    model.result().dataset("grid1").set("parmin1", 0.4);
    model.result().dataset("grid1").set("parmax1", 0.48);
    model.result().dataset("grid1").set("parmin2", 0.4);
    model.result().dataset("grid1").set("parmax2", 0.48);
    model.result().dataset("grid1").set("parmin3", 0.05);
    model.result().dataset("grid1").set("parmax3", 0.15);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.logI");
    model.result("pg1").feature("rtrj1").feature("col1").set("descr", "\u5f3a\u5ea6\u5bf9\u6570");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("filt1").set("type", "logical");
    model.result("pg1").feature("rtrj1").feature("filt1").set("logicalexpr", "gop.fs==2");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u6cf3\u6c60\u533a\u7684\u7126\u6563\u9762");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6cf3\u6c60\u533a\u7684\u7126\u6563\u9762");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "gop.wall1.bacc1.rpb");
    model.result("pg2").feature("surf1").set("descr", "\u7d2f\u79ef\u53d8\u91cf rpb");
    model.result("pg2").feature("surf1").set("smooth", "everywhere");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").set("coloring", "uniform");
    model.result("pg2").feature("surf2").set("color", "gray");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection().named("box1");
    model.result("pg2").run();

    model.title("Vdara\u00ae \u7126\u6563\u9762");

    model
         .description("Vdara\u00ae \u6700\u521d\u5728\u62c9\u65af\u7ef4\u52a0\u65af\u5f00\u4e1a\u65f6\uff0c\u5728\u6cf3\u6c60\u8fb9\u4f11\u95f2\u7684\u6e38\u5ba2\u5728\u7279\u5b9a\u6708\u4efd\u7684\u7279\u5b9a\u65f6\u6bb5\u4f1a\u611f\u5230\u9177\u70ed\u96be\u8010\u3002\u8fd9\u79cd\u9177\u70ed\u662f\u9152\u5e97\u5357\u4fa7\u5f2f\u66f2\u53cd\u5c04\u7684\u592a\u9633\u8f90\u5c04\u5f15\u8d77\u7684\u3002\u672c\u4f8b\u6f14\u793a\u5728\u9996\u6b21\u62a5\u544a\u51fa\u73b0\u8fd9\u79cd\u95ee\u9898\u7684\u65f6\u95f4\u548c\u65e5\u671f\u524d\u540e\uff0c\u6cf3\u6c60\u533a\u57df\u5982\u4f55\u4ea7\u751f\u7126\u6563\u9762\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("vdara_caustic_surface.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
