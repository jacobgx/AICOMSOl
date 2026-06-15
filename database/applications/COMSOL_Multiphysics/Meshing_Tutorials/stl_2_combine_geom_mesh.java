/*
 * stl_2_combine_geom_mesh.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:35 by COMSOL 6.3.0.290. */
public class stl_2_combine_geom_mesh {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Meshing_Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");

    model.component().create("mcomp1", "MeshComponent");

    model.geom().create("mgeom1", 3);

    model.mesh().create("mpart1", "mgeom1");
    model.mesh("mpart1").create("imp1", "Import");

    model.component("mcomp1").baseSystem(null);

    model.geom("mgeom1").lengthUnit("mm");

    model.mesh("mpart1").feature("imp1").set("filename", "c3_vertebra.stl");
    model.mesh("mpart1").feature("imp1").set("createdom", true);
    model.mesh("mpart1").feature("imp1").set("facepartition", "minimal");
    model.mesh("mpart1").feature("imp1").importData();
    model.mesh("mpart1").feature("imp1").setIndex("outsel_bnd", "C3", 0);
    model.mesh("mpart1").create("fill1", "FillHoles");
    model.mesh("mpart1").feature("fill1").set("createdom", true);
    model.mesh("mpart1").feature("fill1").selection().named("imp1_mesh");
    model.mesh("mpart1").run("fill1");

    model.component().create("mcomp2", "MeshComponent");

    model.geom().create("mgeom2", 3);

    model.mesh().create("mpart2", "mgeom2");
    model.mesh("mpart2").create("imp1", "Import");

    model.component("mcomp2").baseSystem(null);

    model.geom("mgeom2").lengthUnit("mm");

    model.mesh("mpart2").feature("imp1").set("filename", "intdiscC_3-4.stl");
    model.mesh("mpart2").feature("imp1").set("createdom", true);
    model.mesh("mpart2").feature("imp1").set("facepartition", "minimal");
    model.mesh("mpart2").feature("imp1").importData();
    model.mesh("mpart2").feature("imp1").setIndex("outsel_bnd", "C3-C4 disc", 0);
    model.mesh("mpart2").create("edg1", "CreateEdges");
    model.mesh("mpart2").feature("edg1").set("edgespec", "meshedge");
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.550540924072266, 0, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 1.2008996009826651, 0, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.79849243164065, 0, 2);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.394841194152836, 1, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 0.9365212023258205, 1, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.73487186431885, 1, 2);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.467785835266113, 2, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 0.4278857856988905, 2, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.74988746643065, 2, 2);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.768284320831304, 3, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -0.11785925924778001, 3, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.8425588607788, 3, 2);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.980187892913825, 4, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 0.064084231853485, 4, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.91245555877685, 4, 2);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 9.835389137268065, 5, 0);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", 0.8742076754570001, 5, 1);
    model.mesh("mpart2").feature("edg1").setIndex("coordsel", -29.8834047317505, 5, 2);
    model.mesh("mpart2").feature("edg1").set("selfac", true);
    model.mesh("mpart2").run("edg1");
    model.mesh("mpart2").create("dele1", "DeleteEntities");
    model.mesh("mpart2").feature("dele1").selection().named("edg1_selfac");
    model.mesh("mpart2").run("dele1");
    model.mesh("mpart2").create("fill1", "FillHoles");
    model.mesh("mpart2").feature("fill1").set("createdom", true);
    model.mesh("mpart2").feature("fill1").selection().named("imp1_mesh");
    model.mesh("mpart2").run("fill1");

    model.component().create("mcomp3", "MeshComponent");

    model.geom().create("mgeom3", 3);

    model.mesh().create("mpart3", "mgeom3");
    model.mesh("mpart3").create("imp1", "Import");

    model.component("mcomp3").baseSystem(null);

    model.geom("mgeom3").lengthUnit("mm");

    model.mesh("mpart3").feature("imp1").set("filename", "c4_vertebra.stl");
    model.mesh("mpart3").feature("imp1").set("createdom", true);
    model.mesh("mpart3").feature("imp1").set("facepartition", "minimal");
    model.mesh("mpart3").feature("imp1").importData();
    model.mesh("mpart3").feature("imp1").set("stltoltype", "absolute");
    model.mesh("mpart3").feature("imp1").set("stltolabs", "5e-4[mm]");
    model.mesh("mpart3").feature("imp1").importData();
    model.mesh("mpart3").feature("imp1").setIndex("outsel_dom", "C4", 0);
    model.mesh("mpart3").feature("imp1").setIndex("outsel_bnd", "C4", 0);

    model.component().create("mcomp4", "MeshComponent");

    model.geom().create("mgeom4", 3);

    model.mesh().create("mpart4", "mgeom4");
    model.mesh("mpart4").create("imp1", "Import");

    model.component("mcomp4").baseSystem(null);

    model.geom("mgeom4").lengthUnit("mm");

    model.mesh("mpart4").feature("imp1").set("source", "sequence");
    model.mesh("mpart4").feature("imp1").set("buildsource", true);
    model.mesh("mpart4").feature("imp1").set("sequence", "mpart1");
    model.mesh("mpart4").feature("imp1").set("unmesheddom", true);
    model.mesh("mpart4").feature("imp1").importData();
    model.mesh("mpart4").create("imp2", "Import");
    model.mesh("mpart4").feature("imp2").set("source", "sequence");
    model.mesh("mpart4").feature("imp2").set("buildsource", true);
    model.mesh("mpart4").feature("imp2").set("sequence", "mpart2");
    model.mesh("mpart4").feature("imp2").set("unmesheddom", true);
    model.mesh("mpart4").feature("imp2").importData();
    model.mesh("mpart4").create("imp3", "Import");
    model.mesh("mpart4").feature("imp3").set("source", "sequence");
    model.mesh("mpart4").feature("imp3").set("buildsource", true);
    model.mesh("mpart4").feature("imp3").set("sequence", "mpart3");
    model.mesh("mpart4").feature("imp3").set("unmesheddom", true);
    model.mesh("mpart4").feature("imp3").importData();
    model.mesh("mpart4").feature("imp3").create("tr1", "Transform");
    model.mesh("mpart4").feature("imp3").feature("tr1").set("displ", new String[]{"0", "0", "1[mm]"});
    model.mesh("mpart4").run("imp3");

    model.view("view5").set("clippingactive", true);
    model.view("view5").clip().create("plane1", "ClipPlane");
    model.view("view5").clip("plane1").set("orientationaxes", new double[][]{{-1, 0, 0}, {0, -1, 0}, {0, 0, 1}});
    model.view("view5").clip("plane1").set("position", new double[]{2.7920684814453516, 0, -30.637358188629154});
    model.view("view5").clip("plane1").set("translationamount", 4.304107463836671);
    model.view("view5").clip("plane1")
         .set("orientationaxes", new String[][]{{"0", "1", "0"}, {"0", "0", "1"}, {"1", "0", "0"}});
    model.view("view5").clip("plane1").set("invertclipping", true);
    model.view("view5").set("clipshowcappedfaces", true);
    model.view("view5").set("clipcappedfacescolorize", false);

    model.mesh("mpart4").create("uni1", "Union");
    model.mesh("mpart4").feature("uni1").set("toltype", "absolute");
    model.mesh("mpart4").feature("uni1").set("abstol", "6e-4[mm]");
    model.mesh("mpart4").feature("uni1").set("placement", "linear");
    model.mesh("mpart4").run("uni1");
    model.mesh("mpart4").create("join1", "JoinEntities");
    model.mesh("mpart4").feature("join1").selection().set(1, 3, 4, 5);
    model.mesh("mpart4").run("join1");

    model.view("view5").set("renderwireframe", true);
    model.view("view5").set("rendermesh", false);
    model.view("view5").set("clippingactive", false);

    model.mesh("mpart4").create("mrg1", "MergeEntities");
    model.mesh("mpart4").feature("mrg1").selection().set(6, 7, 8, 9, 10, 11);
    model.mesh("mpart4").run("mrg1");
    model.mesh("mpart4").create("join2", "JoinEntities");
    model.mesh("mpart4").feature("join2").selection().geom("mgeom4", 2);
    model.mesh("mpart4").feature("join2").selection().set(3, 6, 7, 8);
    model.mesh("mpart4").run("join2");

    model.view("view5").set("rendermesh", true);
    model.view("view5").set("showgrid", false);
    model.view("view5").set("showaxisorientation", false);
    model.view("view5").set("showgrid", true);
    model.view("view5").set("showaxisorientation", true);

    model.mesh("mpart4").create("pln1", "IntersectPlane");
    model.mesh("mpart4").feature("pln1").set("quickplane", "zx");
    model.mesh("mpart4").feature("pln1").set("selabove", true);
    model.mesh("mpart4").feature("pln1").set("selaboveshow", "bnd");
    model.mesh("mpart4").feature("pln1").set("selbelow", true);
    model.mesh("mpart4").feature("pln1").set("selbelowshow", false);
    model.mesh("mpart4").run("pln1");
    model.mesh("mpart4").create("dele1", "DeleteEntities");
    model.mesh("mpart4").feature("dele1").selection().named("pln1_dombelow");
    model.mesh("mpart4").run("dele1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("source", "sequence");
    model.component("comp1").mesh("mesh1").feature("imp1").set("buildsource", true);
    model.component("comp1").mesh("mesh1").feature("imp1").set("sequence", "mpart4");
    model.component("comp1").mesh("mesh1").feature("imp1").set("unmesheddom", true);
    model.component("comp1").mesh("mesh1").feature("imp1").importData();
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh2").autoMeshSize(7);
    model.component("comp1").mesh("mesh2").run();
    model.component("comp1").mesh("mesh2").stat().selection().geom(3);
    model.component("comp1").mesh("mesh2").stat().selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh2").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh2").stat().selection().allGeom();

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.title("STL \u5bfc\u5165 1 - \u4fee\u590d\u548c\u7ec4\u5408 STL \u6587\u4ef6");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5904\u7406\u5bfc\u5165\u7684\u8868\u9762\u7f51\u683c\u3002\u5176\u4e2d\u7684\u64cd\u4f5c\u8bf4\u660e\u8be6\u7ec6\u4ecb\u7ecd\u4e86\u5982\u4f55\u5bfc\u5165\u4e24\u4e2a\u690e\u9aa8\u548c\u4e00\u4e2a\u690e\u95f4\u76d8\u7684 STL \u6587\u4ef6\uff0c\u4ee5\u53ca\u5982\u4f55\u4fee\u590d\u5b54\u3001\u9519\u4f4d\u7684\u7f51\u683c\u9876\u70b9\u548c\u76f8\u4ea4\u5355\u5143\u3002\u6b64\u5916\uff0c\u672c\u6559\u7a0b\u8fd8\u8fdb\u4e00\u6b65\u6f14\u793a\u4e86\u5982\u4f55\u5c06\u8fd9\u4e9b\u7f51\u683c\u8fdb\u884c\u7ec4\u5408\u5e76\u4f7f\u5176\u76f8\u4ea4\uff0c\u91cd\u65b0\u5212\u5206\u8868\u9762\u7f51\u683c\uff0c\u4ee5\u53ca\u5982\u4f55\u751f\u6210\u56db\u9762\u4f53\u7f51\u683c\u4ee5\u4fbf\u5728\u751f\u6210\u7684\u57df\u4e2d\u8fdb\u884c\u4eff\u771f\u3002");

    model.label("stl_1_repair_imported_meshes.mph");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d_rod", "3.5[mm]", "Rod diameter");
    model.param().set("c3_L", "11[mm]", "C3 screw thread length");
    model.param().set("c4_L", "12[mm]", "C3 screw thread length");
    model.param().set("t_angle", "10[deg]", "Transverse angle");
    model.param().set("s_angle", "35[deg]", "Sagittal angle");
    model.param().set("c3_xw", "26[mm]", "C3 entry coordinate");
    model.param().set("c3_yw", "1.5[mm]", "C3 entry coordinate");
    model.param().set("c3_zw", "15[mm]", "C3 entry coordinate");
    model.param().set("c4_xw", "38.5[mm]", "C4 entry coordinate");
    model.param().set("c4_yw", "1[mm]", "C4 entry coordinate");
    model.param().set("c4_zw", "16.5[mm]", "C4 entry coordinate");

    model.geom().load(new String[]{"part1"}, "polyaxial_screw_geom_sequence.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("imp1", "Import");

    model.component("comp1").mesh("mesh1").create("imp2", "Import");
    model.component("comp1").mesh("mesh1").feature("imp2").set("source", "geom");
    model.component("comp1").mesh("mesh1").feature("imp2").set("geom", "geom1");

    model.component("comp1").geom("geom1").feature("imp1").set("type", "mesh");
    model.component("comp1").geom("geom1").feature("imp1").set("mesh", "mpart4");
    model.component("comp1").geom("geom1").feature("imp1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("wp1");
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", "c3_zw");
    model.component("comp1").geom("geom1").feature("wp1").set("displ", new String[]{"-c3_xw", "-c3_yw"});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "thread_l", "c3_L");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "sagittal_angle", "s_angle");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "transverse_angle", "t_angle");
    model.component("comp1").geom("geom1").feature("pi1").set("workplanepart", "wp3");
    model.component("comp1").geom("geom1").feature("pi1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepobj", "pi1_csel1", true);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepobj", "pi1_csel2", true);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u7d2f\u79ef\u9009\u62e9 2");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetoobj", "pi1_csel2", "csel2");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").nodeGroup().create("grp2");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").nodeGroup("grp1").remove("wp2", false);
    model.component("comp1").geom("geom1").nodeGroup("grp2").add("wp2");
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp2").set("quicky", "c4_zw");
    model.component("comp1").geom("geom1").feature("wp2").set("displ", new String[]{"-c4_xw", "-c4_yw"});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "thread_l", "c4_L");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "sagittal_angle", "s_angle");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "transverse_angle", "t_angle");
    model.component("comp1").geom("geom1").feature("pi2").set("workplanepart", "wp3");
    model.component("comp1").geom("geom1").feature("pi2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepobj", "pi2_csel1", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoobj", "pi2_csel1", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepobj", "pi2_csel2", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetoobj", "pi2_csel2", "csel2");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").nodeGroup().create("grp3");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").nodeGroup("grp2").remove("wp3", false);
    model.component("comp1").geom("geom1").nodeGroup("grp3").add("wp3");
    model.component("comp1").geom("geom1").feature("wp3").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp3").selection("offsetvertex").set("pi1(2)", 1);
    model.component("comp1").geom("geom1").feature("wp3").set("quickorigin", "vertexproj");
    model.component("comp1").geom("geom1").feature("wp3").selection("originvertex").set("pi1(2)", 1);
    model.component("comp1").geom("geom1").feature("wp3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp3").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", "d_rod/2");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("cm1", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm1").selection("ent").set("pi1(2)", 1);
    model.component("comp1").geom("geom1").run("cm1");
    model.component("comp1").geom("geom1").create("cm2", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm2").selection("ent").set("pi1(1)", 1);
    model.component("comp1").geom("geom1").run("cm2");
    model.component("comp1").geom("geom1").create("cm3", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm3").selection("ent").set("pi2(2)", 1);
    model.component("comp1").geom("geom1").run("cm3");
    model.component("comp1").geom("geom1").create("cm4", "CentroidMeasurement");
    model.component("comp1").geom("geom1").feature("cm4").selection("ent").set("pi2(1)", 1);
    model.component("comp1").geom("geom1").run("cm4");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("table", new String[][]{{"geom1.cm1.x", "geom1.cm1.y", "geom1.cm1.z"}, 
         {"geom1.cm2.x", "geom1.cm2.y", "geom1.cm2.z"}, 
         {"geom1.cm3.x", "geom1.cm3.y", "geom1.cm3.z"}, 
         {"geom1.cm4.x", "geom1.cm4.y", "geom1.cm4.z"}});
    model.component("comp1").geom("geom1").feature("pol1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("pol1").setAttribute("construction", "on");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("swe1", "Sweep");
    model.component("comp1").geom("geom1").feature("swe1").set("includefinal", false);
    model.component("comp1").geom("geom1").feature("swe1").set("crossfaces", true);
    model.component("comp1").geom("geom1").feature("swe1").set("manualdir", false);
    model.component("comp1").geom("geom1").feature("swe1").selection("enttosweep").named("wp3");
    model.component("comp1").geom("geom1").feature("swe1").set("crossfaces", false);
    model.component("comp1").geom("geom1").feature("swe1").selection("edge").named("pol1");
    model.component("comp1").geom("geom1").feature("swe1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("swe1").setAttribute("construction", "off");
    model.component("comp1").geom("geom1").run("swe1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").nodeGroup("grp3").remove("del1", false);
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("csel2");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh1").feature("imp2").set("meshsize", 3);
    model.component("comp1").mesh("mesh1").feature("imp2").importData();
    model.component("comp1").mesh("mesh1").feature("imp2").problem("info").set("coordsel", "5");
    model.component("comp1").mesh("mesh1").create("uni1", "Union");
    model.component("comp1").mesh("mesh1").run("uni1");
    model.component("comp1").mesh("mesh1").create("join1", "JoinEntities");
    model.component("comp1").mesh("mesh1").feature("join1").selection().set(4, 5, 7, 8);

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").mesh("mesh1").run("join1");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh2").run();

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);
    model.component("comp1").view("view1").set("rendermesh", false);
    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom(3);
    model.component("comp1").view("view1").hideEntities("hide1").add(1);
    model.component("comp1").view("view1").hideEntities("hide1").add(4);
    model.component("comp1").view("view1").hideEntities("hide1").add(5);
    model.component("comp1").view("view1").hideEntities("hide1").add(6);
    model.component("comp1").view("view1").hideObjects().clear();
    model.component("comp1").view("view1").hideEntities().clear();
    model.component("comp1").view("view1").set("rendermesh", true);
    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").mesh("mesh2").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh2").stat().selection().allGeom();

    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.title("STL \u5bfc\u5165 2 - \u7ed3\u5408\u51e0\u4f55\u4e0e\u5bfc\u5165\u7684\u7f51\u683c");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5c06\u201cSTL \u5bfc\u5165 1 - \u4fee\u590d\u548c\u7ec4\u5408 STL \u6587\u4ef6\u201d\u6559\u7a0b\u4e2d\u5bfc\u5165\u7684\u7f51\u683c\u4e0e\u4e24\u4e2a\u690e\u5f13\u6839\u87ba\u9489\u548c\u4e00\u4e2a\u56fa\u5b9a\u6746\u7684\u53c2\u6570\u5316\u51e0\u4f55\u76f8\u7ed3\u5408\u3002\u5176\u4e2d\u7684\u64cd\u4f5c\u8bf4\u660e\u8be6\u7ec6\u4ecb\u7ecd\u4e86\u5982\u4f55\u52a0\u8f7d\u87ba\u9489\u7684\u51e0\u4f55\u96f6\u4ef6\u3001\u5982\u4f55\u5b9a\u4f4d\u5e76\u5c06\u5176\u4e0e\u690e\u9aa8\u7684\u7f51\u683c\u76f8\u7ed3\u5408\uff0c\u4ee5\u5f62\u6210\u8ba1\u7b97\u57df\u3002\u6700\u540e\uff0c\u5bf9\u7ec4\u5408\u7684\u690e\u9aa8\u548c\u87ba\u9489\u7684\u8868\u9762\u7f51\u683c\u91cd\u65b0\u8fdb\u884c\u7f51\u683c\u5212\u5206\uff0c\u5e76\u4f7f\u7528\u56db\u9762\u4f53\u7f51\u683c\u586b\u5145\u8fd9\u4e9b\u57df\u3002");

    model.mesh("mpart1").clearMesh();
    model.mesh("mpart2").clearMesh();
    model.mesh("mpart3").clearMesh();
    model.mesh("mpart4").clearMesh();
    model.component("comp1").mesh("mesh2").clearMesh();

    model.label("stl_2_combine_geom_mesh.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
