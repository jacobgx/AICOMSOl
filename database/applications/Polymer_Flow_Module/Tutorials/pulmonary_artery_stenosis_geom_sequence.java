/*
 * pulmonary_artery_stenosis_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:09 by COMSOL 6.3.0.290. */
public class pulmonary_artery_stenosis_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Polymer_Flow_Module\\Tutorials");

    model.component().create("mcomp1", "MeshComponent");

    model.geom().create("mgeom1", 3);

    model.mesh().create("mpart1", "mgeom1");
    model.mesh("mpart1").create("imp1", "Import");
    model.mesh("mpart1").feature("imp1").set("filename", "pulmonary_artery_stenosis_mesh.mphbin");
    model.mesh("mpart1").feature("imp1").importData();
    model.mesh("mpart1").create("edg1", "CreateEdges");
    model.mesh("mpart1").feature("edg1").set("edgespec", "meshedge");
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.025474513285015567, 0, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05300163474086225, 0, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.06285868311038151, 0, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.02882189543018464, 1, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05376491659015595, 1, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.061381714006174454, 1, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.030975205657410854, 2, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05344556168574242, 2, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05802667913487032, 2, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.03127001906263595, 3, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.052175887615079714, 3, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05400271560175294, 3, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.029926804420967845, 4, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.049717995267243284, 4, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05149142052129319, 4, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.027385809620836102, 5, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.04701073651666218, 5, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05047984850105791, 5, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.024222180439672798, 6, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.04574020824221641, 6, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05017567231851772, 6, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.02124249525487873, 7, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.04549752625558551, 7, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05113297630835324, 7, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.018918073090635684, 8, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.04543944519436713, 8, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05317819527787116, 8, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.017721555132013584, 9, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.046673939298892464, 9, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05574683049261853, 9, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.018078636650821357, 10, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.049179917482312804, 10, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.058379977361690705, 10, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.019600350971719256, 11, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.05101821677718443, 11, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.06072565265250042, 11, 2);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.02212936293007646, 12, 0);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.052024285955543134, 12, 1);
    model.mesh("mpart1").feature("edg1").setIndex("coordsel", 0.0624360061590074, 12, 2);
    model.mesh("mpart1").run("edg1");
    model.mesh("mpart1").create("fac1", "CreateFaces");
    model.mesh("mpart1").feature("fac1").set("createdom", true);
    model.mesh("mpart1").feature("fac1").selection().add(34, 35);
    model.mesh("mpart1").feature("fac1").set("groupadjedg", false);
    model.mesh("mpart1").run("fac1");
    model.mesh("mpart1").create("ada1", "Adapt");
    model.mesh("mpart1").feature("ada1").set("method", "modify");
    model.mesh("mpart1").feature("ada1").set("sizeexpr", "0.003");
    model.mesh("mpart1").run("ada1");
    model.mesh("mpart1").create("remf1", "RemeshFaces");
    model.mesh("mpart1").feature("remf1").selection().all();
    model.mesh("mpart1").feature("remf1").feature("size").set("table", "cfd");
    model.mesh("mpart1").feature("remf1").feature("size").set("hauto", 4);
    model.mesh("mpart1").run("remf1");
    model.mesh("mpart1").create("dom1", "CreateDomains");
    model.mesh("mpart1").run("dom1");
    model.mesh("mpart1").create("ftet1", "FreeTet");
    model.mesh("mpart1").feature("ftet1").feature("size").set("table", "cfd");
    model.mesh("mpart1").feature("ftet1").feature("size").set("hauto", 6);
    model.mesh("mpart1").run("ftet1");
    model.mesh("mpart1").create("bl1", "BndLayer");
    model.mesh("mpart1").feature("bl1").create("blp", "BndLayerProp");
    model.mesh("mpart1").feature("bl1").set("sharpcorners", "trim");
    model.mesh("mpart1").feature("bl1").feature("blp").selection().set(2, 4, 5, 6, 8, 9, 12, 13, 17, 18);
    model.mesh("mpart1").feature("bl1").feature("blp").set("blnlayers", 2);
    model.mesh("mpart1").feature("bl1").feature("blp").set("blhminfact", 5);
    model.mesh("mpart1").run("fin");

    model.title(null);

    model.description("");

    model.label("pulmonary_artery_stenosis_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
